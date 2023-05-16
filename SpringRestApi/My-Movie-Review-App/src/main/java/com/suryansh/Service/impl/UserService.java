package com.suryansh.Service.impl;

import com.suryansh.Entity.Role;
import com.suryansh.Entity.User;
import com.suryansh.Exception.SpringShowException;
import com.suryansh.Model.LoginModel;
import com.suryansh.Model.UserSignUpModel;
import com.suryansh.Repository.UserRepository;
import com.suryansh.dto.AllUsersDto;
import com.suryansh.dto.UserLoginDto;
import com.suryansh.mail.MailService;
import com.suryansh.mail.NotificationEmailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final JwtService jwtService;
    private final MailService mailService;

    @Value("${serverPort}")
    private String serverPort;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, JwtService jwtService, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    public UserLoginDto registerNewUser(UserSignUpModel userModel) {
        // Check user is already registered or not .
        var checkUser = userRepository.findByUsername(userModel.getUsername());
        if (checkUser.isPresent()) throw new SpringShowException("Username is already present !! ");
        String verificationToken = UUID.randomUUID().toString();
        var user = User.builder()
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .userPic(null)
                .isVerified(false)
                .verifyToken(verificationToken)
                .role(Role.USER)
                .build();
        final UserDetails userDetails = jwtService.getJwtTokenForUser(user);
        String jwtToken = jwtUtils.generateToken(userDetails);
        try {
            userRepository.save(user);
            logger.info("User {} is added to database ", user.getUsername());
            return new UserLoginDto(new
                    UserLoginDto.user(user.getUsername(), user.getEmail(),
                    false, user.getRole().name(), user.getUserPic()), jwtToken);
        } catch (Exception e) {
            logger.error("Unable to save user {} ", userModel, e);
            throw new SpringShowException("Unable to save user !! " + userModel);
        }
    }

    public UserLoginDto loginUser(LoginModel model) {
        jwtService.authenticate(model);
        User user = userRepository.findByUsername(model.getUsername())
                .orElseThrow(() -> new SpringShowException("Wrong Credentials !! " + model));
        final UserDetails userDetails = jwtService.getJwtTokenForUser(user);
        String jwtToken = jwtUtils.generateToken(userDetails);
        return new UserLoginDto(new
                UserLoginDto.user(user.getUsername(), user.getEmail(),
                false, user.getRole().name(), user.getUserPic()), jwtToken);
    }

    public AllUsersDto getAllUsers() {
        var users = userRepository.findAll();
        int managers = 0;
        List<AllUsersDto.User> resUser = new ArrayList<>();
        for (User ur : users) {
            AllUsersDto.User newUser = new
                    AllUsersDto.User(ur.getId(), ur.getUsername(), ur.getRole(), ur.getUserPic());
            resUser.add(newUser);
            if (ur.getRole().equals(Role.MANAGER)) managers++;
        }
        return new AllUsersDto(users.size() - managers, managers, resUser);
    }

    public String changeUserRoleInDb(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new SpringShowException("Unable to find user of id " + userId));
        switch (user.getRole()) {
            case USER -> user.setRole(Role.MANAGER);
            case MANAGER -> user.setRole(Role.USER);
            default -> throw new SpringShowException("You are already admin !!");
        }
        try {
            userRepository.save(user);
            logger.info("Role of user {} is changed to {} ", user.getUsername(), user.getRole());
            return "Role is changed for user " + user.getUsername() + " to " + user.getRole();
        } catch (Exception e) {
            logger.error("Unable to change of user " + user.getUsername());
            throw new SpringShowException("Unable to change of user " + user.getUsername());
        }
    }

    public UserLoginDto getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + auth.getName()));
        return new UserLoginDto(new
                UserLoginDto.user(user.getUsername(), user.getEmail(),
                false, user.getRole().name(), user.getUserPic()), "jwtToken");
    }

    public UserLoginDto changePasswordInDb(String oldPassword, String newPassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + auth.getName()));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) throw new
                SpringShowException("Old Password is not matching !!");
        else {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        final UserDetails userDetails = jwtService.getJwtTokenForUser(user);
        String jwtToken = jwtUtils.generateToken(userDetails);
        try {
            userRepository.save(user);
            logger.info("User {} changed its password successfully ", user.getUsername());
            return new UserLoginDto(new
                    UserLoginDto.user(user.getUsername(), user.getEmail(),
                    false, user.getRole().name(), user.getUserPic()), jwtToken);
        } catch (Exception e) {
            logger.error("Unable to update password for User {} ", user.getUsername() + e);
            throw new SpringShowException("Unable to update password !! ");
        }
    }

    public CompletableFuture<String> sendPasswordEmail(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (!user.isVerified()) throw new SpringShowException("Please verify your email first !!");
        user.setVerifyToken(UUID.randomUUID().toString());
        try {
            String link = serverPort + "send-forgot-password-form/" + username + "/" + user.getVerifyToken();
            NotificationEmailDto emailDto = NotificationEmailDto.builder()
                    .userName(username)
                    .sender("Movie Review App")
                    .subject("Forgot Password")
                    .recipient(user.getEmail())
                    .body(link)
                    .build();
            mailService.sendForgotPasswordEmail(emailDto);
            userRepository.save(user);
            return CompletableFuture.completedFuture("Mail sent successfully");
        } catch (Exception e) {
            logger.error("sendPasswordEmail: Unable to send email to user {} ", username, e);
            throw new SpringShowException("Unable to send request for password reset ");
        }
    }

    public void resendAccountVerifyEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + auth.getName()));
        if (user.isVerified())
            throw new SpringShowException("Your account is already verified !!");
        try {
            String link = serverPort + "verify-account/" + user.getUsername() + "/" + user.getVerifyToken();
            NotificationEmailDto emailDto = NotificationEmailDto.builder()
                    .userName(user.getUsername())
                    .sender("Movie Review App")
                    .subject("Email Verification")
                    .recipient(user.getEmail())
                    .body(link)
                    .build();
            mailService.sendAuthVerificationMail(emailDto);
        } catch (Exception e) {
            logger.error("Unable to re-send verification email for user {} ", user.getUsername(), e);
        }
    }

    public ResponseEntity<?> getVerifyStatusOfUser(String authorizationHeader) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + auth.getName()));
        if (user.isVerified()) {
            String jwtToken = authorizationHeader.replace("Bearer ", "");
            return ResponseEntity.ok(new UserLoginDto(new
                    UserLoginDto.user(user.getUsername(), user.getEmail(),
                    true, user.getRole().name(), user.getUserPic()), jwtToken));
        }
        return ResponseEntity.ok(false);
    }
}
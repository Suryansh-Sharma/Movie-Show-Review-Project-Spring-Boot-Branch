package com.suryansh.Controller;

import com.suryansh.Entity.User;
import com.suryansh.Exception.SpringShowException;
import com.suryansh.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class MvcController {
    private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    MvcController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/verify-account/{username}/{token}")
    public String verifyAccount(@PathVariable String username, @PathVariable String token, Model model) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (user.isVerified()) {
            model.addAttribute("successMessage", "");
            model.addAttribute("errorMessage", "Your account is already verified !!");
            return "verification-result";
        }
        if (!user.getVerifyToken().equals(token)) {
            model.addAttribute("successMessage", "");
            model.addAttribute("errorMessage", "Your verification session is expired !!,Request new one");
            return "verification-result";
        }
        user.setVerified(true);
        try {
            userRepository.save(user);
            logger.info("Ussr {} account is verified ", username);
            model.addAttribute("successMessage", "Congratulation your account is verified successfully ");
            model.addAttribute("errorMessage", "");
            return "verification-result";
        } catch (Exception e) {
            logger.error("Unable to verify account for user {} ", username, e);
            throw new SpringShowException("Unable verify you account !!");
        }
    }

    @GetMapping("/send-forgot-password-form/{username}/{token}")
    public String showForgotPasswordForm(@PathVariable String username, @PathVariable String token, Model model) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (!token.equals(user.getVerifyToken())) {
            model.addAttribute("successMessage", "");
            model.addAttribute("errorMessage", "Verification session is expired , please request new one !! ");
            return "password-reset-result";
        }
        model.addAttribute("username", username);
        model.addAttribute("token", user.getVerifyToken());
        return "forgot-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(Model model, @RequestParam String username
            , @RequestParam String password, @RequestParam String token) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringShowException("Unable to find user " + username));
        if (!user.getVerifyToken().equals(token)) {
            model.addAttribute("successMessage", "");
            model.addAttribute("errorMessage", "Verification session is expired ,please request new one !! ");
            return "password-reset-result";
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setVerifyToken(UUID.randomUUID().toString());
        try {
            userRepository.save(user);
            model.addAttribute("successMessage", "Password changed successfully !!");
            model.addAttribute("errorMessage", "");
            logger.info("Password changed successfully !! for user {} ", username);
            return "password-reset-result";
        } catch (Exception e) {
            logger.error("Unable to save user {} for resetPassword ", user, e);
            throw new SpringShowException("Unable to reset password for user " + user);
        }
    }
}

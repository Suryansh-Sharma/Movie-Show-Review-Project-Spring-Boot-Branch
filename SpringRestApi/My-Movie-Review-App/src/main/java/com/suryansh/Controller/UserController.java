package com.suryansh.Controller;
/*
 * This Controller handle Authorization Api ,User Registration.
 */

import com.suryansh.Model.LoginModel;
import com.suryansh.Model.UserSignUpModel;
import com.suryansh.Service.impl.UserService;
import com.suryansh.dto.AllUsersDto;
import com.suryansh.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public UserLoginDto registerNewUser(@RequestBody @Valid UserSignUpModel user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/account-login")
    public UserLoginDto loginUserAccount(@RequestBody @Valid LoginModel model) {
        return userService.loginUser(model);
    }

    @GetMapping("/all-users-with-role")
    @PreAuthorize("hasRole('ADMIN')")
    public AllUsersDto allUsersWithRoles() {
        return userService.getAllUsers();
    }

    @PostMapping("/change-user-role/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String changeUserRole(@PathVariable Long userId) {
        return userService.changeUserRoleInDb(userId);
    }

    @GetMapping("/current-user-detail")
    public UserLoginDto getCurrentUser() {
        return userService.getCurrentUserDetails();
    }

    @PostMapping("/change-password/{oldPassword}/{newPassword}")
    public UserLoginDto changeUserPassword(@PathVariable String oldPassword, @PathVariable String newPassword) {
        return userService.changePasswordInDb(oldPassword, newPassword);
    }

    @GetMapping("/is-verified")
    public ResponseEntity<?> checkUserIfVerified(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.getVerifyStatusOfUser(authorizationHeader);
    }

    @PostMapping("/resend-verification-email/")
    public void resendVerificationEmail() {
        userService.resendAccountVerifyEmail();
    }

    @PostMapping("/send-forgot-password-mail/{username}")
    public CompletableFuture<String> sendForgotPasswordEmail(@PathVariable String username) {
        return userService.sendPasswordEmail(username);
    }
}
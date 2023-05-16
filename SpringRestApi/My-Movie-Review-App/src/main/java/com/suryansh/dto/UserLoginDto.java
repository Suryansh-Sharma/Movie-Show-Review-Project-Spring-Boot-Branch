package com.suryansh.dto;

public record UserLoginDto(user User, String jwt_token) {
    public record user(String username, String email, boolean isVerified, String role, String profilePic) {
    }
}

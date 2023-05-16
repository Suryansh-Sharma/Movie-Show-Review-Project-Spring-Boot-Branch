package com.suryansh.dto;

import com.suryansh.Entity.Role;

import java.util.List;

public record AllUsersDto(int total_users, int total_managers, List<User> users) {
    public record User(Long user_id, String username, Role role, String profile_pic) {
    }
}

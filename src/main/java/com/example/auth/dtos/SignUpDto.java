package com.example.auth.dtos;

import com.example.auth.user.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role
){}

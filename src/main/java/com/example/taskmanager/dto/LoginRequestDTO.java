package com.example.taskmanager.dto;

import lombok.Data;

@Data // Generates getters, setters, and constructors automatically
public class LoginRequestDTO {
    private String username;
    private String password;
}

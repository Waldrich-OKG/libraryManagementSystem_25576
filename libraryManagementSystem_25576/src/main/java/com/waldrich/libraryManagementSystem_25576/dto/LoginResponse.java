package com.waldrich.libraryManagementSystem_25576.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private boolean success;
}

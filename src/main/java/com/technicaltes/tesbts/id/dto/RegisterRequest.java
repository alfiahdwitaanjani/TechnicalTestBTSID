package com.technicaltes.tesbts.id.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String passwordConfirmation;
}

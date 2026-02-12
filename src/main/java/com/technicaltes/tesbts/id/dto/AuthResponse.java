package com.technicaltes.tesbts.id.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String authentication_token;
}

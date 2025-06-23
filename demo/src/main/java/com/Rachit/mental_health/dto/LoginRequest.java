package com.Rachit.mental_health.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private long UserId;
    private String email;
    private String password;
}

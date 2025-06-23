package com.Rachit.mental_health.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long UserId;
    private String Username;
    private String email;
    private String password;
}

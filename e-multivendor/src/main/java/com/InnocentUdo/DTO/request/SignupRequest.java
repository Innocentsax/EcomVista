package com.InnocentUdo.DTO.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String fullName;
    private String otp;
}

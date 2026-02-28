package com.example.SpringJWT_Auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String name;
    private String email;
    private String address;
}


package com.example.SpringJWT_Auth.controller;

import com.example.SpringJWT_Auth.dto.ProfileRequest;
import com.example.SpringJWT_Auth.entity.Profile;
import com.example.SpringJWT_Auth.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public Profile addProfile(@RequestBody ProfileRequest request,
                              Authentication authentication) {

        return profileService.addProfile(
                request,
                authentication.getName()
        );
    }
}

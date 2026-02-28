package com.example.SpringJWT_Auth.controller;

import com.example.SpringJWT_Auth.dto.ProfileRequest;
import com.example.SpringJWT_Auth.entity.Profile;
import com.example.SpringJWT_Auth.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProfileRepository profileRepository;

    @PutMapping("/profile/{id}")
    public Profile update(@PathVariable Long id,
                          @RequestBody ProfileRequest request) {

        Profile profile = profileRepository.findById(id).orElseThrow();
        profile.setName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setAddress(request.getAddress());
        return profileRepository.save(profile);
    }

    @DeleteMapping("/profile/{id}")
    public String delete(@PathVariable Long id) {
        profileRepository.deleteById(id);
        return "Deleted";
    }
}


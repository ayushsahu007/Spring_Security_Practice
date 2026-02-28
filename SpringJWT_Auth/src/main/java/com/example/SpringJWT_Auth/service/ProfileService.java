package com.example.SpringJWT_Auth.service;

import com.example.SpringJWT_Auth.dto.ProfileRequest;
import com.example.SpringJWT_Auth.entity.Profile;
import com.example.SpringJWT_Auth.entity.User;
import com.example.SpringJWT_Auth.repository.ProfileRepository;
import com.example.SpringJWT_Auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Profile addProfile(ProfileRequest request, String username) {

        User user = userRepository.findByUsername(username).orElseThrow();

        if (profileRepository.existsByUser(user)) {
            throw new RuntimeException("Profile already added once");
        }

        Profile profile = new Profile();
        profile.setName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setAddress(request.getAddress());
        profile.setUser(user);

        return profileRepository.save(profile);
    }
}

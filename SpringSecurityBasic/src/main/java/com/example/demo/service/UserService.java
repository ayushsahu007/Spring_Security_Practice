package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            System.out.println("User id not found");
        }
        return user;
    }
    public User updateUser(Integer id, User updatedUser) {
        User user = userRepository.getById(id);
        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = userRepository.getById(id);
        userRepository.delete(user);
    }

}

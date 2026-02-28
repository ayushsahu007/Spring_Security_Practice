package com.spring.security.Durgesh_Security_JWT.service;

import com.spring.security.Durgesh_Security_JWT.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(),"Ayush Sahu","ayushsahu007ay@gmailc.co"));
        store.add(new User(UUID.randomUUID().toString(),"Ramesh","ramwesh@gmailc.co"));
        store.add(new User(UUID.randomUUID().toString(),"Rahul Rathore","Rahuk@gmailc.co"));
        store.add(new User(UUID.randomUUID().toString(),"Dinesh Karthik","Dinesh@gmailc.co"));
    }

    public List<User> getUsers() {
        return this.store;
    }

}

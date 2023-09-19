package com.abhi.auth.impl;/*
    @author jadon
*/

import com.abhi.auth.entity.User;
import com.abhi.auth.repositary.UserRepositary;
import com.abhi.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositary userRepositary;

    @Override
    public User create(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return this.userRepositary.save(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepositary.findAll();
    }

    @Override
    public User getUser(String userId) {
        return this.userRepositary.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found !!"));
    }

    @Override
    public User update(User user) {
        User user1 = this.userRepositary.findById(user.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found !!"));
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRoles(user.getRoles());
        return this.userRepositary.save(user1);
    }

    @Override
    public void delete(String userId) {
        User user = this.userRepositary.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found !!"));
        this.userRepositary.delete(user);
    }
}

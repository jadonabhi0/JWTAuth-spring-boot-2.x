package com.abhi.auth.service;/*
    @author jadon
*/

import com.abhi.auth.entity.User;
import com.abhi.auth.impl.UserDetailsImpl;
import com.abhi.auth.repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepositary userRepositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepositary.findByUserName(username);
        return new UserDetailsImpl(user);
    }


}

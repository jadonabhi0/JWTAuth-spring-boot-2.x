package com.abhi.auth.service;/*
    @author jadon
*/

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("abhi")){
            return new User("abhi", "abc123", new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}

package com.abhi.auth.controller;/*
    @author jadon
*/

import com.abhi.auth.model.JwtRequest;
import com.abhi.auth.model.JwtResponse;
import com.abhi.auth.service.CustomUserDetailService;
import com.abhi.auth.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController

public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    Logger logger = LoggerFactory.getLogger(TokenController.class);

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> tokenGenerator(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            // authenticate user with the username and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
            logger.info("User successfully authenticated  with there credentials");


            // if any exception occurs while authenticating then catch block will automatically sense
        }catch (BadCredentialsException e){
            logger.info("Bad Credentials !!");
            throw new Exception("Bad Credentials !!");
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            logger.info("User not found !!");
            throw new Exception("User not found !!");
        }


        // feting the user-details object
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUserName());

        // generating the JWT-token
        String generateToken = this.jwtUtils.generateToken(userDetails);
        logger.info("Token generated");

        // returning JWT-response
        return new ResponseEntity<>(new JwtResponse(jwtRequest.getUserName(), generateToken), HttpStatus.OK);

    }
}

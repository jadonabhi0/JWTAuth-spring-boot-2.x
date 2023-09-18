package com.abhi.auth.controller;/*
    @author jadon
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> welcomeHome(){
        return ResponseEntity.ok("Welcome home ! This Page is private for unauthorized peoples");
    }

}

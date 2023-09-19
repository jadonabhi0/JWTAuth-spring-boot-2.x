package com.abhi.auth.controller;/*
    @author jadon
*/

import com.abhi.auth.entity.User;
import com.abhi.auth.repositary.UserRepositary;
import com.abhi.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid  @RequestBody User user){
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(String userId){
        userService.delete(userId);
        return ResponseEntity.ok("User deleted successfully !!");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody User user){
        User update = this.userService.update(user);
        return ResponseEntity.ok(update);
    }


}

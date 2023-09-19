package com.abhi.auth.service;/*
    @author jadon
*/

import com.abhi.auth.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> getAll();

    User getUser(String userId);

    User update(User user);

    void delete(String userId);

}

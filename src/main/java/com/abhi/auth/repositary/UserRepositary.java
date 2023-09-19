package com.abhi.auth.repositary;/*
    @author jadon
*/

import com.abhi.auth.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositary extends MongoRepository<User, String> {
    User findByUserName(String userName);
}

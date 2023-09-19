package com.abhi.auth.entity;/*
    @author jadon
*/

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {

    @Id
    private String userId;

//    @NotBlank(message = "username can't be null")
    @NotEmpty(message = "username can't be null")
    @Size(min = 3, max = 20)
    private String userName;

    @Email
    @NotBlank(message = "email can't be blank")
    private String email;


    @Size(min = 4, max = 120)
    private String password;

    @NotBlank(message = "Enter your role ")
    private Set<String> roles = new HashSet<>();



}

package com.abhi.auth.payload.request;/*
    @author jadon
*/

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {

    private String userName;

    private String password;

}

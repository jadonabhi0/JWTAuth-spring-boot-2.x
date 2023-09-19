package com.abhi.auth.payload.response;/*
    @author jadon
*/

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String userName;

    private String jwtToken;
}

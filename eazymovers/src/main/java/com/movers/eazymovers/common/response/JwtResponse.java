package com.movers.eazymovers.common.response;

import lombok.Data;

@Data
public class JwtResponse{
    private String jwtToken;
    private String userName;
    private String firstName;
    private String lastName;
}

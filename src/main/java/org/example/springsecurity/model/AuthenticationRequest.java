package org.example.springsecurity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// To define the input argument of the authentication method
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }
}

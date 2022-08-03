package com.dimastasky.foodkeeper.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String fullname;

    @Getter
    @Setter
    private String email;

    @Getter
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String fullname, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

}

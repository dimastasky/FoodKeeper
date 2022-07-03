package com.example.accounts_service.payload.request.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String fullname;

    private Set<String> role;

    @NotBlank
    private String password;
}

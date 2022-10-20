package com.dimastasky.foodkeeper.models.dtos.userDTO;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
public class UserCreationDTO implements DTOEntity {
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

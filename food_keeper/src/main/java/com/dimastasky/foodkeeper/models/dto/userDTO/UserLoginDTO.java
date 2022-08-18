package com.dimastasky.foodkeeper.models.dto.userDTO;

import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginDTO implements DTOEntity {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

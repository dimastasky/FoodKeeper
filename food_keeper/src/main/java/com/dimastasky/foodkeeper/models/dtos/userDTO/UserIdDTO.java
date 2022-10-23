package com.dimastasky.foodkeeper.models.dtos.userDTO;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserIdDTO implements DTOEntity {
    @NotBlank
    private Long userId;
}

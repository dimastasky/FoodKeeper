package com.dimastasky.foodkeeper.models.dtos.user_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements DTOEntity {

    private Long id;

    private String username;

    private String email;

    private String fullname;

}

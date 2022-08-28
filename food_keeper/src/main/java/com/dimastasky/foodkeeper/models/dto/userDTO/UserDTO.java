package com.dimastasky.foodkeeper.models.dto.userDTO;

import com.dimastasky.foodkeeper.models.dto.DTOEntity;
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

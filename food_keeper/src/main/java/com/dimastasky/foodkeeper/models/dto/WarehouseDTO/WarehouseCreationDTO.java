package com.dimastasky.foodkeeper.models.dto.WarehouseDTO;

// TODO: Написать класс

import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class WarehouseCreationDTO implements DTOEntity {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private Integer warehouseType;

}

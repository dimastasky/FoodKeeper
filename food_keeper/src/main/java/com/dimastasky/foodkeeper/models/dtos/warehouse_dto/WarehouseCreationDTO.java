package com.dimastasky.foodkeeper.models.dtos.warehouse_dto;

// TODO: Написать класс

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class WarehouseCreationDTO implements DTOEntity {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    private Integer warehouseType;

    private Long userId;

}

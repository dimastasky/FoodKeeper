package com.dimastasky.foodkeeper.models.dtos.warehouse_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO implements DTOEntity {

    private Long id;

    private String name;

    private Integer warehouseType;
}

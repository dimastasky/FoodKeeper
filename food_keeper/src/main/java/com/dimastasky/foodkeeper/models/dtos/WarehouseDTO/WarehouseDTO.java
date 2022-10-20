package com.dimastasky.foodkeeper.models.dtos.WarehouseDTO;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WarehouseDTO implements DTOEntity {

    private Long id;

    private String name;

    private Set<WarehouseTypeDTO> warehouseType;
}

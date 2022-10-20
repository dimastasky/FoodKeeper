package com.dimastasky.foodkeeper.models.dtos.WarehouseDTO;

import com.dimastasky.foodkeeper.models.enums.EWarehouseType;
import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseTypeDTO implements DTOEntity {

    private Integer id;

    private EWarehouseType name;
}

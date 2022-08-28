package com.dimastasky.foodkeeper.models.dto.WarehouseDTO;

import com.dimastasky.foodkeeper.models.data.EWarehouseType;
import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseTypeDTO implements DTOEntity {

    private Integer id;

    private EWarehouseType name;
}

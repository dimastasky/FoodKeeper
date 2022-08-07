package com.dimastasky.foodkeeper.payload.request.foodkeeper;

// TODO: Написать класс

import com.dimastasky.foodkeeper.models.data.EWarehouseType;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class WarehouseRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private Integer warehouseType;

}

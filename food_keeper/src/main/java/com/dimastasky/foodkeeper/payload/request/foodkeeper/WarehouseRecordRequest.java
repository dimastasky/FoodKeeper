package com.dimastasky.foodkeeper.payload.request.foodkeeper;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class WarehouseRecordRequest {
    @NotNull
    private Long user;

    @NotNull
    private Long product;

    @NotNull
    private Integer count;

    private Date bestBefore;

}

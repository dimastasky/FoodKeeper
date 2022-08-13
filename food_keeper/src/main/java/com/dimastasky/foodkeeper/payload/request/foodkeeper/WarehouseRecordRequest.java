package com.dimastasky.foodkeeper.payload.request.foodkeeper;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class WarehouseRecordRequest {

    @NotBlank
    private Long product;

    @NotBlank
    private Integer count;

    private Date bestBefore;

}

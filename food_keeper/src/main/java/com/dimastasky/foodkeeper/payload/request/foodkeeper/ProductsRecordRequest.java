package com.dimastasky.foodkeeper.payload.request.foodkeeper;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductsRecordRequest {
    @NotBlank
    private Integer count;


}
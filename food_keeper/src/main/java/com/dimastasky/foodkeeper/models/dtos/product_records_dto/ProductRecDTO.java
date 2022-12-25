package com.dimastasky.foodkeeper.models.dtos.product_records_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class ProductRecDTO implements DTOEntity {

    private Long id;

    private Product product;

    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private Date bestBefore;
}

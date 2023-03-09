package com.dimastasky.foodkeeper.models.dtos.product_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO implements DTOEntity {
    private Long id;
    @NotBlank
    private String name;
    private Integer foodTypeId;
    private Float calories;
    private Float fat;
    private Float protein;
    private Float carbs;
    private Float weight;

}

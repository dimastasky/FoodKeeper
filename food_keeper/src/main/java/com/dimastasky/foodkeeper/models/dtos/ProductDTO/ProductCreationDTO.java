package com.dimastasky.foodkeeper.models.dtos.ProductDTO;


import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

// TODO: Написать класс
@Getter
@Setter
public class ProductCreationDTO implements DTOEntity {
    @NotBlank
    private String name;
    private Integer foodTypeId;
    private Float energy;
    private Float fat;
    private Float protein;
    private Float carbs;
    private Float weight;
}

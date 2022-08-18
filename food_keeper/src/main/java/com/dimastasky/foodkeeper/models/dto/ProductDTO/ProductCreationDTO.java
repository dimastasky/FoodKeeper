package com.dimastasky.foodkeeper.models.dto.ProductDTO;


import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

// TODO: Написать класс
@Getter
@Setter
public class ProductCreationDTO implements DTOEntity {
    @NotBlank
    private String name;

    private Integer foodType;

    private Double energy;

    private Double fat;

    private Double protein;

    private Double carbs;

    private Double weight;
}

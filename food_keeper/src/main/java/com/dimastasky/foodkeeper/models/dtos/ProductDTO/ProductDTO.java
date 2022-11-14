package com.dimastasky.foodkeeper.models.dtos.ProductDTO;

import com.dimastasky.foodkeeper.models.enums.EFoodType;
import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
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

    public ProductDTO(Long id, String name, Integer foodTypeId, Float calories,
                      Float fat, Float protein, Float carbs, Float weight) {
        this.id = id;
        this.name = name;
        this.foodTypeId = foodTypeId;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.weight = weight;
    }
}

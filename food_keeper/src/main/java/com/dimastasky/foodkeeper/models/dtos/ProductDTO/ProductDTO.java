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
    private Double calories;
    private Double fat;
    private Double protein;
    private Double carbs;
    private Double weight;

    public ProductDTO(Long id, String name, Integer foodTypeId, Double calories,
                      Double fat, Double protein, Double carbs, Double weight) {
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

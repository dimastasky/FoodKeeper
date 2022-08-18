package com.dimastasky.foodkeeper.models.dto.ProductDTO;

import com.dimastasky.foodkeeper.models.data.EFoodType;
import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDTO implements DTOEntity {
    private Long id;

    @NotBlank
    private String name;

    private EFoodType productType;

    private Double calories;

    private Double fat;

    private Double protein;

    private Double carbs;

    private Double weight;

    public ProductDTO(Long id, String name, EFoodType productType, Double calories,
                      Double fat, Double protein, Double carbs, Double weight) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.weight = weight;
    }
}

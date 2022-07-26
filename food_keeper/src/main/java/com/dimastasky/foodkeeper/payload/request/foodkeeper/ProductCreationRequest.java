package com.dimastasky.foodkeeper.payload.request.foodkeeper;


import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

// TODO: Написать класс
@Getter
@Setter
public class ProductCreationRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Set<FoodType> foodTypeSet;

    @NotBlank
    private Double energy;

    @NotBlank
    private Double fat;

    @NotBlank
    private Double protein;

    @NotBlank
    private Double carbs;

    @NotBlank
    private Integer weight;
}

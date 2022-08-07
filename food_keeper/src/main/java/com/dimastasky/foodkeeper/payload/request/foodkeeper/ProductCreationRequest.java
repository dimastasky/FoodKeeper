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

    private Integer foodType;


    private Double energy;


    private Double fat;


    private Double protein;


    private Double carbs;


    private Double weight;
}

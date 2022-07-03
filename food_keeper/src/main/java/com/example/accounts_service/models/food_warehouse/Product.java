package com.example.accounts_service.models.food_warehouse;

import com.example.accounts_service.models.food_warehouse.FoodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
}
)
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="product_types", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<FoodType> foodTypes = new HashSet<>();

//    @NotBlank
    private Double energy;

//    @NotBlank
    private Double fat;

//    @NotBlank
    private Double protein;

//    @NotBlank
    private Double carbs;

//    @NotBlank
    private Integer weight;

    public Product() {

    }

    public Product(String name,
                   Set<FoodType> foodTypes,
                   Double energy,
                   Double fat,
                   Double protein,
                   Double carbs,
                   Integer weight) {
        this.name = name;
        this.foodTypes = foodTypes;
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.weight = weight;
    }
}

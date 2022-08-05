package com.dimastasky.foodkeeper.models.food_warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name="product_types", joinColumns = @JoinColumn(name = "product_id"),
//    inverseJoinColumns = @JoinColumn(name = "type_id"))
//    private Set<FoodType> foodTypes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "foodtype_id", referencedColumnName = "id")
    private FoodType foodType;

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

    public Product(Long id, String name, FoodType foodType, Double energy, Double fat, Double protein, Double carbs, Integer weight) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.weight = weight;
    }
}

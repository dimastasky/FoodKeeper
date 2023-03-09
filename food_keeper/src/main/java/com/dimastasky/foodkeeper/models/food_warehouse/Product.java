package com.dimastasky.foodkeeper.models.food_warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private Float energy;

//    @NotBlank
    private Float fat;

//    @NotBlank
    private Float protein;

//    @NotBlank
    private Float carbs;

//    @NotBlank
    private Float packageWeight;

    //todo: Добавить единицы измерения eType (grams, kilograms, liters etc.)

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<FoodRecord> foodRecords;

    public Product() {

    }

    public Product(Long id, String name, FoodType foodType, Float energy, Float fat, Float protein, Float carbs, Float weight) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.energy = energy;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.packageWeight = weight;
    }
}

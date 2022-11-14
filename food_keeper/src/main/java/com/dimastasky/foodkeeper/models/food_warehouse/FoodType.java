package com.dimastasky.foodkeeper.models.food_warehouse;

import com.dimastasky.foodkeeper.models.enums.EFoodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "foodTypes")
@Getter
@Setter
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFoodType name;

    //todo: Название категории на русском

    @JsonIgnore
    @OneToMany(mappedBy = "foodType")
    private Set<Product> foodType;

    public FoodType() {

    }

    public FoodType(EFoodType foodType) {
        this.name = foodType;
    }
}

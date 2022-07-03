package com.example.accounts_service.models.food_warehouse;

import com.example.accounts_service.models.data.EFoodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "foodType")
@Getter
@Setter
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFoodType foodType;

    public FoodType() {

    }

    public FoodType(Integer id, EFoodType foodType) {
        this.id = id;
        this.foodType = foodType;
    }
}

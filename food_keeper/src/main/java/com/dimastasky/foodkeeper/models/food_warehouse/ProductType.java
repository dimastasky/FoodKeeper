package com.dimastasky.foodkeeper.models.food_warehouse;

import com.dimastasky.foodkeeper.models.data.EFoodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "foodType")
@Getter
@Setter
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFoodType name;

    @JsonIgnore
    @OneToMany(mappedBy = "productType")
    private Set<Product> type_product;

    public ProductType() {

    }

    public ProductType(EFoodType foodType) {
        this.name = foodType;
    }
}

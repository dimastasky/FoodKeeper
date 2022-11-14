package com.dimastasky.foodkeeper.models.dtos.ProductDTO;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        Long id = product.getId();
        String name = product.getName();
        Integer foodTypeId = product.getFoodType().getId();
        Float calories = product.getEnergy();
        Float fat = product.getFat();
        Float protein = product.getProtein();
        Float carbs = product.getCarbs();
        Float weight = product.getPackageWeight();
        return new ProductDTO(id, name, foodTypeId, calories,
                                fat, protein, carbs, weight);
    }
}

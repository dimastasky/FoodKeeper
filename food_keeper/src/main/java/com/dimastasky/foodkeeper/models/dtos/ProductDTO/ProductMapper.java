package com.dimastasky.foodkeeper.models.dtos.ProductDTO;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        Long id = product.getId();
        String name = product.getName();
        Integer foodTypeId = product.getFoodType().getId();
        Double calories = product.getEnergy();
        Double fat = product.getFat();
        Double protein = product.getProtein();
        Double carbs = product.getCarbs();
        Double weight = product.getPackageWeight();
        return new ProductDTO(id, name, foodTypeId, calories,
                                fat, protein, carbs, weight);
    }
}

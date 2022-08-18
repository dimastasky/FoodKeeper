package com.dimastasky.foodkeeper.models.dto.ProductDTO;

import com.dimastasky.foodkeeper.models.data.EFoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.food_warehouse.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        Long id = product.getId();
        String name = product.getName();
        EFoodType productType = product.getProductType().getName();
        Double calories = product.getEnergy();
        Double fat = product.getFat();
        Double protein = product.getProtein();
        Double carbs = product.getCarbs();
        Double weight = product.getPackageWeight();
        return new ProductDTO(id, name, productType, calories,
                                fat, protein, carbs, weight);
    }
}

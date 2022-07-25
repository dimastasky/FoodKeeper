package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

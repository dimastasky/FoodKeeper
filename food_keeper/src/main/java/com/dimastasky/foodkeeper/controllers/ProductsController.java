package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.ProductDTO.ProductDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.dtos.ProductDTO.ProductCreationDTO;
import com.dimastasky.foodkeeper.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductsService service;

    @GetMapping("/all-products")
    @Cacheable("products")
    public List<Product> getAllProducts() {
        return service.findAllProducts();
    }

    @PostMapping("/product")
    @CacheEvict(value = "products", allEntries = true)
    public ProductCreationDTO addProduct(@Valid @RequestBody ProductCreationDTO productCreationDTO) {
        return service.addProduct(productCreationDTO);
    }

    @GetMapping("/product")
    public ProductDTO getProduct(@RequestParam("id") Long id) {
        return service.findProductById(id);
    }

    @PutMapping("/product")
    @CacheEvict(value = "products", allEntries = true)
    public ProductDTO editProduct(@RequestBody ProductDTO productDTO) {
        return service.findProductById(productDTO.getId());
    }

    @DeleteMapping("/product")
    @CacheEvict(value = "products", allEntries = true)
    public ResponseEntity<?> deleteProduct(@RequestParam("id") Long id) {
        service.deleteProductById(id);
        return new ResponseEntity<>("Product with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-foodtypes")
    @Cacheable("foodTypes")
    public List<FoodType> getAllFoodTypes() {
        return service.findAllProductTypes();
    }

}

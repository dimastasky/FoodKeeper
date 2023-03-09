package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.product_dto.ProductDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.dtos.product_dto.ProductCreationDTO;
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
public class ProductController {

    private final ProductsService service;

    //todo: refactor mappings

    @GetMapping("/all-products")
    @Cacheable("products")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @GetMapping("/product")
    @Cacheable("products")
    public ProductDTO findProductById(@RequestParam("id") Long id) {
        return service.findProductById(id);
    }

    @PostMapping("/product")
    @CacheEvict(value = "products", allEntries = true)
    public ProductCreationDTO addProduct(@Valid @RequestBody ProductCreationDTO productDTO) {
        return service.addProduct(productDTO);
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

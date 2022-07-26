package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.ProductCreationRequest;
import com.dimastasky.foodkeeper.repository.RoleRepository;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.FoodTypeRepository;
import com.dimastasky.foodkeeper.repository.warehouse.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FoodTypeRepository foodTypeRepository;

    @GetMapping("/all-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) { return productRepository.getReferenceById(id); }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreationRequest productCreationRequest) {
        Product product = new Product();

        product.setName(productCreationRequest.getName());
        product.setFoodTypes(productCreationRequest.getFoodTypeSet());
        product.setEnergy(productCreationRequest.getEnergy());
        product.setFat(productCreationRequest.getFat());
        product.setProtein(productCreationRequest.getProtein());
        product.setCarbs(productCreationRequest.getCarbs());
        product.setWeight(productCreationRequest.getWeight());

        productRepository.save(product);

        return ResponseEntity.ok("Product created.");
    }





}

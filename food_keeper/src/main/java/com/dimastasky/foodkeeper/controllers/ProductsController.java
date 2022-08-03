package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.data.EFoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Set<String> strFoodTypes = productCreationRequest.getFoodType();
        Set<FoodType> foodTypeSet = new HashSet<>();

        if (strFoodTypes == null) {
            FoodType defType = foodTypeRepository.findByName(EFoodType.DEFAULT)
                    .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
            foodTypeSet.add(defType);
        } else {
            strFoodTypes.forEach(type -> {
                switch (type) {
                    case "fruits" -> {
                        FoodType fruitsType = foodTypeRepository.findByName(EFoodType.FRUITS)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(fruitsType);
                    }
                    case "vegetables" -> {
                        FoodType vegsType = foodTypeRepository.findByName(EFoodType.VEGETABLES)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(vegsType);
                    }
                    case "conserves" -> {
                        FoodType conservesType = foodTypeRepository.findByName(EFoodType.CONSERVES)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(conservesType);
                    }
                    case "grain" -> {
                        FoodType grainType = foodTypeRepository.findByName(EFoodType.GRAIN)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(grainType);
                    }
                    case "meat" -> {
                        FoodType meatType = foodTypeRepository.findByName(EFoodType.MEAT)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(meatType);
                    }
                    case "poultry" -> {
                        FoodType poultryType = foodTypeRepository.findByName(EFoodType.POULTRY)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(poultryType);
                    }
                    case "fish" -> {
                        FoodType fishType = foodTypeRepository.findByName(EFoodType.FISH)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(fishType);
                    }
                    case "sauce" -> {
                        FoodType sauceType = foodTypeRepository.findByName(EFoodType.SAUCE)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(sauceType);
                    }
                    case "drinks" -> {
                        FoodType drinksType = foodTypeRepository.findByName(EFoodType.DRINKS)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(drinksType);
                    }
                    default -> {
                        FoodType defType = foodTypeRepository.findByName(EFoodType.DEFAULT)
                                .orElseThrow(() -> new RuntimeException("Error: FoodType is not found!"));
                        foodTypeSet.add(defType);
                    }
                }
            });
        }
        product.setFoodTypes(foodTypeSet);
        product.setEnergy(productCreationRequest.getEnergy());
        product.setFat(productCreationRequest.getFat());
        product.setProtein(productCreationRequest.getProtein());
        product.setCarbs(productCreationRequest.getCarbs());
        product.setWeight(productCreationRequest.getWeight());

        productRepository.save(product);

        return ResponseEntity.ok("Product created.");
    }





}

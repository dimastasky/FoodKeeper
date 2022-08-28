package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dto.ProductDTO.ProductDTO;
import com.dimastasky.foodkeeper.models.dto.ProductDTO.ProductMapper;
import com.dimastasky.foodkeeper.models.food_warehouse.ProductType;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.dto.ProductDTO.ProductCreationDTO;
import com.dimastasky.foodkeeper.repository.warehouse.ProductTypeRepository;
import com.dimastasky.foodkeeper.repository.warehouse.ProductRepository;
import com.dimastasky.foodkeeper.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    ProductService productService;

    ProductMapper mapper;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/all-products")
    public List<ProductDTO> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        modelMapper.map(products, ProductDTO.class);
        return productService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        Product product = productRepository.getReferenceById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    @PostMapping("/product")
    public ProductCreationDTO createProduct(@Valid @RequestBody ProductCreationDTO productCreationDTO) {
        Product product = new Product();

        product.setName(productCreationDTO.getName());
        product.setProductType(productTypeRepository.getReferenceById(productCreationDTO.getFoodType()));
        product.setEnergy(productCreationDTO.getEnergy());
        product.setFat(productCreationDTO.getFat());
        product.setProtein(productCreationDTO.getProtein());
        product.setCarbs(productCreationDTO.getCarbs());
        product.setPackageWeight(productCreationDTO.getWeight());

        productRepository.save(product);

        return productCreationDTO;
    }

    @GetMapping("/get-foodtypes")
    public List<ProductType> getAllFoodTypes() {
        return productTypeRepository.findAll();
    }



}

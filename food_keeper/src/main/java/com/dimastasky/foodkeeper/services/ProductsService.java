package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.dtos.product_dto.ProductCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.product_dto.ProductDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import com.dimastasky.foodkeeper.repository.food.ProductRepository;
import com.dimastasky.foodkeeper.repository.food.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository repository;
    private final ProductTypeRepository productTypeRepository;
    ModelMapper modelMapper = new ModelMapper();

    public ProductDTO findProductById(Long id) {
        Product product = repository.getReferenceById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public ProductCreationDTO addProduct(ProductCreationDTO params) {
        Product product = new Product();

        product.setName(params.getName());
        product.setFoodType(productTypeRepository.getReferenceById(params.getFoodTypeId()));
        product.setEnergy(params.getEnergy());
        product.setFat(params.getFat());
        product.setProtein(params.getProtein());
        product.setCarbs(params.getCarbs());
        product.setPackageWeight(params.getWeight());

        repository.save(product);

        return params;
    }

    public ProductDTO editProduct(ProductDTO params) {
        Product product = repository.getReferenceById(params.getId());

        product.setName(params.getName());
        product.setFoodType(productTypeRepository.getReferenceById(params.getFoodTypeId()));
        product.setEnergy(params.getCalories());
        product.setFat(params.getFat());
        product.setProtein(product.getProtein());
        product.setCarbs(params.getCarbs());
        product.setPackageWeight(params.getWeight());

        repository.save(product);

        return params;
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    //todo: перенести в отдельный CRUD сервис
    public List<FoodType> findAllProductTypes() {
        return productTypeRepository.findAll();
    }
}

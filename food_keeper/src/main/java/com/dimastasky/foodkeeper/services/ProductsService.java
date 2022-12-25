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

    public ProductCreationDTO addProduct(ProductCreationDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setFoodType(productTypeRepository.getReferenceById(productDTO.getFoodTypeId()));
        product.setEnergy(productDTO.getEnergy());
        product.setFat(productDTO.getFat());
        product.setProtein(productDTO.getProtein());
        product.setCarbs(productDTO.getCarbs());
        product.setPackageWeight(productDTO.getWeight());

        repository.save(product);

        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = repository.getReferenceById(productDTO.getId());

        product.setName(productDTO.getName());
        product.setFoodType(productTypeRepository.getReferenceById(productDTO.getFoodTypeId()));
        product.setEnergy(productDTO.getCalories());
        product.setFat(productDTO.getFat());
        product.setProtein(product.getProtein());
        product.setCarbs(productDTO.getCarbs());
        product.setPackageWeight(productDTO.getWeight());

        repository.save(product);

        return productDTO;
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    //todo: перенести в отдельный CRUD сервис
    public List<FoodType> findAllProductTypes() {
        return productTypeRepository.findAll();
    }
}

package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.dtos.ProductDTO.ProductCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.ProductDTO.ProductDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.dimastasky.foodkeeper.models.food_warehouse.ProductType;
import com.dimastasky.foodkeeper.repository.warehouse.ProductRepository;
import com.dimastasky.foodkeeper.repository.warehouse.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository repository;
    private final ProductTypeRepository productTypeRepository;
    ModelMapper modelMapper = new ModelMapper();

    public List<Product> getAll() {
        return repository.findAll();
    }

    public ProductCreationDTO addProduct(ProductCreationDTO productCreationDTO) {
        Product product = new Product();

        product.setName(productCreationDTO.getName());
        product.setProductType(productTypeRepository.getReferenceById(productCreationDTO.getFoodTypeId()));
        product.setEnergy(productCreationDTO.getEnergy());
        product.setFat(productCreationDTO.getFat());
        product.setProtein(productCreationDTO.getProtein());
        product.setCarbs(productCreationDTO.getCarbs());
        product.setPackageWeight(productCreationDTO.getWeight());

        repository.save(product);

        return productCreationDTO;
    }

    public ProductDTO findProductById(Long id) {
        Product product = repository.getReferenceById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = repository.getReferenceById(productDTO.getId());

        product.setName(productDTO.getName());
        product.setProductType(productTypeRepository.getReferenceById(productDTO.getFoodTypeId()));
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

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public List<ProductType> findAllProductTypes() {
        return productTypeRepository.findAll();
    }
}

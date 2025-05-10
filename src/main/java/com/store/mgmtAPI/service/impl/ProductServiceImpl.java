package com.store.mgmtAPI.service.impl;

import com.store.mgmtAPI.controller.exception.ProductNameAlreadyExistsException;
import com.store.mgmtAPI.repository.ProductRepository;
import com.store.mgmtAPI.repository.model.Product;
import com.store.mgmtAPI.service.IProductService;
import com.store.mgmtAPI.service.dto.ProductDTO;
import com.store.mgmtAPI.service.dto.ProductMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {
    private ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO addProduct(Product product) {
        log.info("Attempting to add new product: {}", product.getName());

        if(productRepository.findByName(product.getName()).isPresent()){
            // product name already exists in DB, not adding it again
            log.error("Failed to add product: {}", product.getName());
            throw  new ProductNameAlreadyExistsException("The product " + product.getName() + " already exists in DB, choose another name and try again ");
        }

        Product savedProd = productRepository.save(product);
        log.info("Successfully added product with ID: {}", savedProd.getId());
        ProductDTO productDTO = ProductMapper.mapToProductDTO(savedProd, new ProductDTO());

        return productDTO;
    }


    @Override
    public List<ProductDTO> getAllProducts() {

        log.info("Attempting to retrieve a list of all products...");

        List<ProductDTO> listOfProductDTO = new ArrayList<>();

        for(Product product : productRepository.findAll()){
            ProductDTO productDTO = ProductMapper.mapToProductDTO(product, new ProductDTO());
            listOfProductDTO.add(productDTO);
        }
        log.info("List of products retrieved successfully.");
        return listOfProductDTO;
    }

    @Override
    public ProductDTO getProduct(String name) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByName(String name) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByDescription(String description) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public boolean deleteAllProducts() {
        return false;
    }

    @Override
    public boolean updateProduct(int id, Product product) {
        return false;
    }
}

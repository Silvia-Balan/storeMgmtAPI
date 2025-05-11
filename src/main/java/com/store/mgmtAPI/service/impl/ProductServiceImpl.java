package com.store.mgmtAPI.service.impl;

import com.store.mgmtAPI.exception.ProductNameAlreadyExistsException;
import com.store.mgmtAPI.exception.ProductNameMismatchException;
import com.store.mgmtAPI.exception.ProductNameNotFoundException;
import com.store.mgmtAPI.repository.ProductRepository;
import com.store.mgmtAPI.model.Product;
import com.store.mgmtAPI.service.IProductService;
import com.store.mgmtAPI.dto.ProductDTO;
import com.store.mgmtAPI.dto.ProductMapper;
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
        log.info("Attempting to add new product: '{}'", product.getName());

        if(productRepository.findByName(product.getName()).isPresent()){
            // product name already exists in DB, not adding it again
            log.error("Failed to add product: '{}'", product.getName());
            throw  new ProductNameAlreadyExistsException("The product " + product.getName() + " already exists in DB, choose another name and try again ");
        }

        Product savedProd = productRepository.save(product);
        log.info("Successfully added product with ID: '{}'", savedProd.getId());
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
        log.info("Attempting to retrieve product '{}'",name);

        Product product = productRepository
                .findByName(name)
                .orElseThrow(() -> {
                    log.error("Product name '{}' not found in DB", name);
                    return new ProductNameNotFoundException("Product name: " + name + " not found in DB");
                });

        log.info("Product '{}' retrieved successfully", name);

        ProductDTO productDTO = ProductMapper.mapToProductDTO(product, new ProductDTO());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProductsByName(String name) {
        log.info("Attempting to retrieve a list of all products containing '{}' in the name", name);

        List<ProductDTO> listOfProductsDTO = new ArrayList<>();

        for(Product product : productRepository.findProductByName(name)){
            ProductDTO productDTO = ProductMapper.mapToProductDTO(product, new ProductDTO());
            listOfProductsDTO.add(productDTO);
        }

        if(listOfProductsDTO.isEmpty()){
            log.error("Product containing '{}' in the name not found", name);
            throw new ProductNameNotFoundException("Product name: " + name + " not found in DB");
        }

        log.info("Product list '{}' retrieved successfully", listOfProductsDTO);

        return listOfProductsDTO;
    }

    @Override
    public List<ProductDTO> getAllProductsByDescription(String description) {
        log.info("Attempting to retrieve a list of all products containing '{}' in the description", description);

        List<ProductDTO> listOfProductsDTO = new ArrayList<>();

        for(Product product : productRepository.findProductByDescription(description)){
            ProductDTO productDTO = ProductMapper.mapToProductDTO(product, new ProductDTO());
            listOfProductsDTO.add(productDTO);
        }

        if(listOfProductsDTO.isEmpty()){
            log.error("Product containing '{}' in the description not found", description);
            throw new ProductNameNotFoundException("Product name: " + description + " not found in DB");
        }

        log.info("Product list '{}' retrieved successfully", listOfProductsDTO);

        return listOfProductsDTO;
    }

    @Override
    public boolean deleteProduct(String name) {

        log.info("Attempting to delete product '{}'", name);

        Product product = productRepository.findByName(name)
                .orElseThrow(() -> {
                    log.error("Product name '{}' not found in DB", name);
                    return new ProductNameNotFoundException("Product name: " + name + " not found in DB");
                });

        productRepository.deleteByName(name);
        log.info("Product '{}' deleted successfully.", name);
        return true;
    }

    @Override
    public boolean deleteAllProducts() {

        log.info("Attempting to delete all products.");
        productRepository.deleteAll();
        log.info("All products have been deleted.");
        return true;
    }

    @Override
    public boolean updateProduct(String nameFromUri, Product product) {
        String productName = product.getName();
        log.info("Attempting to update product '{}'", product);

        if(!nameFromUri.equals(productName)){
            log.error("Product name '{}' not found in DB", productName);
            throw new ProductNameMismatchException("Product name mismatch: URI = " + nameFromUri + "Entity name = " + productName );
        }

        productRepository.updateProduct(productName, product.getDescription(), product.getPrice(), product.getQuantity());
        log.info("Product updated successfully '{}'", product);

        return true;
    }
}

package com.store.mgmtAPI.service;

import com.store.mgmtAPI.repository.model.Product;
import com.store.mgmtAPI.service.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO addProduct(Product product);

    public List<ProductDTO> getAllProducts();
    public ProductDTO getProduct(String name);
    public List<ProductDTO> getAllProductsByName(String name);
    public List<ProductDTO> getAllProductsByDescription(String description);

    boolean deleteProduct(int id);
    boolean deleteAllProducts();

    public boolean updateProduct(int id, Product product);
}

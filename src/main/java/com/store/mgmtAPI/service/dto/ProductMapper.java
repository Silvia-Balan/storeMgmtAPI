package com.store.mgmtAPI.service.dto;

import com.store.mgmtAPI.repository.model.Product;

public class ProductMapper {
    public static ProductDTO mapToProductDTO(Product product, ProductDTO productDTO){
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());

        return productDTO;
    }
}

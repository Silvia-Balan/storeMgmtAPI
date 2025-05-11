package com.store.mgmtAPI.mapper;

import com.store.mgmtAPI.dto.ProductDTO;
import com.store.mgmtAPI.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // inform MapStruct to generate a Spring-managed bean to be injected
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}

package com.store.mgmtAPI.controller;

import com.store.mgmtAPI.repository.model.Product;
import com.store.mgmtAPI.service.IProductService;
import com.store.mgmtAPI.service.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return iProductService.getAllProducts();
    }
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Product is: " + product);
        ProductDTO productDTO = iProductService.addProduct(product);

        URI locationURI = uriComponentsBuilder
                .path("/products/" + productDTO.getName())
                .buildAndExpand(uriComponentsBuilder.toUriString())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(locationURI)
                .body(productDTO);
    }



}

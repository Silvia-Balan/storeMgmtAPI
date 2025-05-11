package com.store.mgmtAPI.controller;

import com.store.mgmtAPI.model.Product;
import com.store.mgmtAPI.service.IProductService;
import com.store.mgmtAPI.dto.ProductDTO;
import org.springframework.http.HttpMethod;
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

    @GetMapping(path = "/product", params = "name")
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@RequestParam String name){
        List<ProductDTO> nameProducts = iProductService.getAllProductsByName(name);

        if(nameProducts.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(nameProducts);
        }
    }

    @GetMapping(path = "/product", params = "description")
    public ResponseEntity<List<ProductDTO>> getAllProductsByDescription(@RequestParam String description){
        List<ProductDTO> descProducts = iProductService.getAllProductsByDescription(description);

        if(descProducts.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(descProducts);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllProducts(){
        iProductService.deleteAllProducts();

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping
    public ResponseEntity<String> putNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @GetMapping(path = "/product/{name}")
    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name){
        ProductDTO productDTO = iProductService.getProduct(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productDTO);
    }

    @DeleteMapping(path = "/product/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name){
        iProductService.deleteProduct(name);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping(path = "/product/{name}")
    public ResponseEntity<String> postNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @PutMapping(path = "/product/{name}")
    public ResponseEntity<String> updateProduct(@PathVariable String name, @RequestBody Product product){
        iProductService.updateProduct(name, product);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsCollectionOfProducts(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)
                .build();
    }

    @RequestMapping(value = "/product", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualRequestParams(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET)
                .build();
    }

    @RequestMapping(value = "/product/{name}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualProduct(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }
}

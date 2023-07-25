package dev.marvin.springrest.controller;

import dev.marvin.springrest.model.Product;
import dev.marvin.springrest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products REST API", description = "RESTful API for products")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Get All Products")
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Operation(summary = "Get Product By ID")
    @GetMapping(value = "/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @Operation(summary = "Create New Product")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @Operation(summary = "Update Product By ID")
    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product productUpdate) {
        return productService.updateProduct(productId, productUpdate);
    }

    @Operation(summary = "Delete Product By ID")
    @DeleteMapping(value = "/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProductById(productId);
    }
}

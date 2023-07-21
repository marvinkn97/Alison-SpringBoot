package dev.marvin.springrest.controller;

import dev.marvin.springrest.model.Product;
import dev.marvin.springrest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product productUpdate) {
        return productService.updateProduct(productId, productUpdate);
    }

    @DeleteMapping(value = "/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProductById(productId);
    }
}

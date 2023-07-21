package dev.marvin.springrest.dao;

import dev.marvin.springrest.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> getProducts();
    Optional<Product> getProductById(Long productId);
    Product saveProduct(Product product);
    void deleteProduct(Product product);
}

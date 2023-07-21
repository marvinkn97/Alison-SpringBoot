package dev.marvin.springrest.service;

import dev.marvin.springrest.controller.ProductController;
import dev.marvin.springrest.dao.ProductDao;
import dev.marvin.springrest.exception.ResourceNotFoundException;
import dev.marvin.springrest.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductDao productDao;

    public ProductService(@Qualifier("jpa") ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProducts() {
        logger.info("Getting products");
        return productDao.getProducts();
    }

    public Product getProductById(Long productId) {
        logger.info("Getting product with given id [%s]".formatted(productId));
        return productDao.getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("product with id [%s] not found".formatted(productId)));
    }

    public Product createProduct(Product newProduct) {
        try {
            logger.info("Saving...");
            return productDao.saveProduct(newProduct);
        } catch (Exception e) {
            logger.info("An error occurred during product saving" + e.getMessage());
        }
        return new Product();
    }

    public Product updateProduct(Long productId, Product productUpdate) {
        Product existingProduct = productDao.getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("product with id [%s] not found".formatted(productId)));

        try {
            existingProduct.setName(productUpdate.getName());
            existingProduct.setCategory(productUpdate.getCategory());
            existingProduct.setDescription(productUpdate.getDescription());
            existingProduct.setPrice(productUpdate.getPrice());
            return productDao.saveProduct(existingProduct);
        } catch (Exception e) {
            logger.info("An error occurred during update of product" + e.getMessage());
        }
        return productUpdate;
    }


    public void deleteProductById(Long productId) {
        Product productToDelete = productDao.getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("product with id [%s] not found".formatted(productId)));
        try {
            productDao.deleteProduct(productToDelete);
        } catch (Exception e) {
            logger.info("An error occurred during deletion of product" + e.getMessage());
        }
    }

}

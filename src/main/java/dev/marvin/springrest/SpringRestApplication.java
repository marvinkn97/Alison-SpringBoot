package dev.marvin.springrest;

import dev.marvin.springrest.model.Product;
import dev.marvin.springrest.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product product = new Product("Test Product", "Test Description", "Test Type", "Test Category", BigDecimal.TEN);
            productRepository.save(product);
        };
    }

}

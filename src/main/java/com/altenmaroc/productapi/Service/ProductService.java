package com.altenmaroc.productapi.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.altenmaroc.productapi.Model.Product;
import com.altenmaroc.productapi.Repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    // Update a product fully (PUT)
    public Optional<Product> updateProductFull(long id, Product updatedProduct) {
        return productRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setCode(updatedProduct.getCode());
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setDescription(updatedProduct.getDescription());
                return productRepository.save(existingProduct);
            });
    }

    // Delete a product
    public boolean deleteProduct(long id) {
        return productRepository.findById(id)
            .map(product -> {
                productRepository.delete(product);
                return true;
            }).orElse(false);
    }

}

package com.altenmaroc.productapi.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altenmaroc.productapi.Model.Product;
import com.altenmaroc.productapi.Service.ProductService;

@RestController
@RequestMapping("/products")

public class ProductController {
  
  private final ProductService productService;

  // Injecting the ProductService
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  // POST /products - Create a new product
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }
  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
       return ResponseEntity.ok(products);
  }

  // GET: Get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT: Update a product fully
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductFull(@PathVariable long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = productService.updateProductFull(id, updatedProduct);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
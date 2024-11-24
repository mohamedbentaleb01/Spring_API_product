package com.altenmaroc.productapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altenmaroc.productapi.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Custom query methods can go here if needed
  // Spring automatically provides the following methods without you writing any code:
  // save(S entity) - to save or update a product.
  // findById(ID id) - to retrieve a product by its ID.
  // findAll() - to get a list of all products.
  // deleteById(ID id) - to delete a product by its ID.
}
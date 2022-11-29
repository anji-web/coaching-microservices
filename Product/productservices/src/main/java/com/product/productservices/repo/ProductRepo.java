package com.product.productservices.repo;

import com.product.productservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

  Product getProductByProductId(int id);
}

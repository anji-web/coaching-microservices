package com.product.productservices.controller;

import com.product.productservices.entity.Product;
import com.product.productservices.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

  @Autowired
  private ProductRepo productRepo;

  @PostMapping("/")
  public String saveProduct(@RequestBody Product product) {
          productRepo.save(product);
          return "Product saved successfully";
  }

  @GetMapping("/")
  public ResponseEntity getProductByProductId(@RequestParam("productId") int productId) {

    return ResponseEntity.ok(productRepo.getProductByProductId(productId));

  }
}

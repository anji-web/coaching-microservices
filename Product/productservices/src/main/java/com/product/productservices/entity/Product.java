package com.product.productservices.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int productId;

  private String productName;

  private String productType;
}

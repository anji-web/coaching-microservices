package com.users.userservices.entity;

import lombok.Data;

@Data
public class ResponseDto {

  private int userId;

  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String phoneNumber;

  private Product product;

}

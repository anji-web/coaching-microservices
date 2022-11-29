package com.users.userservices.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;

  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String phoneNumber;
  private int productId;
}

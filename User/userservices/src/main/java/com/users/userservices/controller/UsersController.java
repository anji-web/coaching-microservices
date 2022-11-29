package com.users.userservices.controller;

import com.users.userservices.entity.ResponseDto;
import com.users.userservices.entity.Users;
import com.users.userservices.repository.UsersRepository;
import com.users.userservices.services.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

  @Autowired
  private UsersService usersService;

  @GetMapping("/")
  public ResponseEntity<List<Users>> getUsers(){
    return ResponseEntity.ok(usersService.getAllUsers());
  }

  @GetMapping("/user-by-id")
  public ResponseEntity<ResponseDto> getUserProduct(@RequestParam("userId") int userId){
    return ResponseEntity.ok(usersService.getUsersByProduct(userId));
  }

  @PostMapping("/")
  public ResponseEntity<String> createUser(@RequestBody Users users){
    boolean result =  usersService.createUsers(users);
    if (result){
        return ResponseEntity.status(HttpStatus.CREATED).body("Data successfully created");
    }else {
      return ResponseEntity.badRequest().body("Created user failed");
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity deleteUser(@RequestParam("userId") int userId) {
    try {
        usersService.deleteUsers(userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity updateUser(@RequestParam("userId") int userId, @RequestBody Users users) {
    try {
        usersService.updateUser(userId, users);
        return ResponseEntity.ok().body("User updated successfully");
    }catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


}

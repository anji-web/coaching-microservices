package com.users.userservices.services;


import com.netflix.discovery.EurekaClient;
import com.users.userservices.entity.Product;
import com.users.userservices.entity.ResponseDto;
import com.users.userservices.entity.Users;
import com.users.userservices.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EurekaClient client;


    public ResponseDto getUsersByProduct(int userId) {
          ResponseDto result = new ResponseDto();
          Users user = usersRepository.findById(userId).get();

          if (user != null) {
                result.setEmail(user.getEmail());
                result.setUsername(user.getUsername());
                result.setFirstName(user.getFirstName());
                result.setLastName(user.getLastName());
                result.setUserId(user.getUserId());
                result.setPhoneNumber(user.getPhoneNumber());
          }
        RestTemplate restTemplate = new RestTemplate();
        String url = client.getNextServerFromEureka("PRODUCT-SERVICE", false).getHomePageUrl();
        Product product = restTemplate.
            getForObject(url+"/product/?productId=" + user.getProductId()
                , Product.class
            );
        result.setProduct(product);

        return result;

    }

    public boolean createUsers(Users user){
          if (user.getUsername() != null) {
                usersRepository.save(user);
                return true;
          }else {
                return false;
          }
    }

    public List<Users> getAllUsers(){
      return usersRepository.findAll();
    }

    public void deleteUsers(int userId) throws Exception {
      Optional<Users> user = usersRepository.findById(userId);
        if (!user.isEmpty()) {
            usersRepository.delete(user.get());
        }else {
            throw new Exception("User not registered in database");
        }
    }


    public void updateUser(int userId, Users userRequest) throws Exception {
      Optional<Users> user = usersRepository.findById(userId);
      if (!user.isEmpty()) {
          user.get().setUsername(userRequest.getUsername());
          user.get().setFirstName(userRequest.getFirstName());
          user.get().setLastName(userRequest.getLastName());
          user.get().setEmail(userRequest.getEmail());
          user.get().setPhoneNumber(userRequest.getPhoneNumber());

          usersRepository.save(user.get());
      }else {
        throw new Exception("User not registered in database");
      }
    }

}

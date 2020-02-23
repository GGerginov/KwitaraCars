package project.demo.service;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import project.demo.domain.entities.Car;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserName(String username);

    UserServiceModel findUserById(String id);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

    List<UserServiceModel> findAllUsers();

    void deleteUser(String id);

    void makeAdmin(String id);

    void makeUser(String id);

    void updateUserUsername(String username,String id);

    void save(UserServiceModel userServiceModel);
}
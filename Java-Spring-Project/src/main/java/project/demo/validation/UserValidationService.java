package project.demo.validation;

import project.demo.service.models.UserServiceModel;

public interface UserValidationService {

    boolean isValid(UserServiceModel userServiceModel);
}

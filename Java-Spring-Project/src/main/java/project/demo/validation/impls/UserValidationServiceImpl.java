package project.demo.validation.impls;

import org.springframework.stereotype.Component;
import project.demo.service.models.UserServiceModel;
import project.demo.validation.UserValidationService;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel userServiceModel) {
        return userServiceModel != null;
    }
}

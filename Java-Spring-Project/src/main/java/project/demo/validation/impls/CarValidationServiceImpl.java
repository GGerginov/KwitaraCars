package project.demo.validation.impls;

import org.springframework.stereotype.Component;
import project.demo.service.models.CarServiceModel;
import project.demo.validation.CarValidationService;

@Component
public class CarValidationServiceImpl implements CarValidationService {
    @Override
    public boolean isValid(CarServiceModel carServiceModel) {
        return carServiceModel != null;
    }
}

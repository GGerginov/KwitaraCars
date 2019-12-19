package project.demo.validation;

import org.springframework.stereotype.Component;
import project.demo.service.models.CarServiceModel;


public interface CarValidationService {

    boolean isValid(CarServiceModel carServiceModel);
}

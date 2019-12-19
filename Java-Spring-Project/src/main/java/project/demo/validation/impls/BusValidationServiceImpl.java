package project.demo.validation.impls;

import org.springframework.stereotype.Component;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;
import project.demo.validation.BusValidationService;
import project.demo.validation.CarValidationService;

@Component
public class BusValidationServiceImpl implements BusValidationService {

    @Override
    public boolean isValid(BusServiceModel busServiceModel) {
        return busServiceModel != null;
    }
}

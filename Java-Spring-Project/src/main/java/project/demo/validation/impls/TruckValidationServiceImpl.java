package project.demo.validation.impls;

import org.springframework.stereotype.Component;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.TruckServiceModel;
import project.demo.validation.BusValidationService;
import project.demo.validation.TruckValidationService;

@Component
public class TruckValidationServiceImpl implements TruckValidationService {


    @Override
    public boolean isValid(TruckServiceModel truckServiceModel) {
        return truckServiceModel!= null;
    }
}

package project.demo.validation;

import project.demo.service.models.TruckServiceModel;


public interface TruckValidationService {

    boolean isValid(TruckServiceModel truckServiceModel);
}

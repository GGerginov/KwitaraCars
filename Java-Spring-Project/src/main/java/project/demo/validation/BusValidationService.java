package project.demo.validation;

import project.demo.service.models.BusServiceModel;


public interface BusValidationService {

    boolean isValid(BusServiceModel busServiceModel);
}

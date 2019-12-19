package project.demo.service;

import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;
import project.demo.service.models.CarServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {

    List<CarServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage);

    void publish(CarServiceModel carServiceModel);

    List<CarServiceModel> getAllBy();

    List<CarServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    CarServiceModel getById(String id);

    void delete(String id);

    List<CarServiceModel> findAllByUserId(String id);


}

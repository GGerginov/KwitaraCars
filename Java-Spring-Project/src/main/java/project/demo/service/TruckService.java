package project.demo.service;

import project.demo.domain.entities.Car;
import project.demo.domain.entities.Truck;
import project.demo.domain.entities.enums.Status;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.TruckServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface TruckService {

    List<TruckServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage);

    void publish(TruckServiceModel truckServiceModel);

    List<TruckServiceModel> getAllBy();

    List<TruckServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    TruckServiceModel getById(String id);

    void delete(TruckServiceModel truckServiceModel);

    List<TruckServiceModel> findAllByUserId(String id);


}

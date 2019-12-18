package project.demo.service;

import project.demo.domain.entities.enums.Status;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;

import java.math.BigDecimal;
import java.util.List;


public interface BusService {

    List<BusServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage);

    void publish(BusServiceModel carServiceModel);

    List<BusServiceModel> getAllBy();

    List<BusServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    BusServiceModel getById(String id);

    void delete(BusServiceModel carServiceModel);

    List<BusServiceModel> findAllByUserId(String id);


}

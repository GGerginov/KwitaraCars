package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.User;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.CarRepository;
import project.demo.repositories.UserRepository;
import project.demo.service.CarService;
import project.demo.service.models.CarServiceModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CarServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage) {
        return this.carRepository.findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(brand, model, status, price, millage)
                .stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void publish(CarServiceModel carServiceModel) {

        Car car = this.modelMapper.map(carServiceModel,Car.class);

        User user = this.userRepository.getByUsername(carServiceModel.getUser().getUsername());

        car.setUser(user);
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public List<CarServiceModel> getAllBy() {
        return this.carRepository.getAllBy().stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.carRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public CarServiceModel getById(String id) {
        return this.modelMapper.map(this.carRepository.getById(id),CarServiceModel.class);
    }

    @Override
    public void delete(CarServiceModel carServiceModel) {
        this.carRepository.delete(this.modelMapper.map(carServiceModel,Car.class));
    }

    @Override
    public List<CarServiceModel> findAllByUserId(String id) {
        return this.carRepository.findAllByUserId(id).stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }
}

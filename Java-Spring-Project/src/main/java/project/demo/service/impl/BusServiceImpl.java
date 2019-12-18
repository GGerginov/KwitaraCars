package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Bus;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.User;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.BusRepository;
import project.demo.repositories.CarRepository;
import project.demo.repositories.UserRepository;
import project.demo.service.BusService;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    private final ModelMapper modelMapper;

    private final BusRepository busRepository;

    private final UserRepository userRepository;

    @Autowired
    public BusServiceImpl(ModelMapper modelMapper, BusRepository busRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BusServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage) {
        return this.busRepository.findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(brand, model, status, price, millage)
                .stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void publish(BusServiceModel busServiceModel) {

        Bus bus = this.modelMapper.map(busServiceModel,Bus.class);

        User user = this.userRepository.getByUsername(busServiceModel.getUser().getUsername());

        bus.setUser(user);
        this.busRepository.saveAndFlush(bus);
    }

    @Override
    public List<BusServiceModel> getAllBy() {
        return this.busRepository.getAllBy().stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<BusServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.busRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public BusServiceModel getById(String id) {
        return this.modelMapper.map(this.busRepository.getById(id),BusServiceModel.class);
    }

    @Override
    public void delete(BusServiceModel busServiceModel) {
        this.busRepository.delete(this.modelMapper.map(busServiceModel,Bus.class));
    }

    @Override
    public List<BusServiceModel> findAllByUserId(String id) {
        return this.busRepository.findAllByUserId(id).stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }

}

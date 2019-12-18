package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.Truck;
import project.demo.domain.entities.User;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.TruckRepository;
import project.demo.repositories.UserRepository;
import project.demo.service.TruckService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.TruckServiceModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TruckServiceImpl implements TruckService {

    private final ModelMapper modelMapper;

    private final TruckRepository truckRepository;

    private final UserRepository userRepository;

    public TruckServiceImpl(ModelMapper modelMapper, TruckRepository truckRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.truckRepository = truckRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TruckServiceModel> findAllByManufacturerAndModelAndStatusAndPriceAndMillage(String brand, String model, Status status, BigDecimal price, Integer millage) {
        return this.truckRepository.findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(brand, model, status, price, millage)
                .stream().map(truck -> this.modelMapper.map(truck,TruckServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void publish(TruckServiceModel truckServiceModel) {

        Truck truck = this.modelMapper.map(truckServiceModel,Truck.class);

        User user = this.userRepository.getByUsername(truckServiceModel.getUser().getUsername());

        truck.setUser(user);
        this.truckRepository.saveAndFlush(truck);
    }

    @Override
    public List<TruckServiceModel> getAllBy() {
        return this.truckRepository.getAllBy().stream().map(truck -> this.modelMapper.map(truck,TruckServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<TruckServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.truckRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(truck -> this.modelMapper.map(truck,TruckServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public TruckServiceModel getById(String id) {
        return this.modelMapper.map(this.truckRepository.getById(id),TruckServiceModel.class);
    }

    @Override
    public void delete(TruckServiceModel truckServiceModel) {
        this.truckRepository.delete(this.modelMapper.map(truckServiceModel,Truck.class));
    }

    @Override
    public List<TruckServiceModel> findAllByUserId(String id) {
        return this.truckRepository.findAllByUserId(id).stream().map(truck -> this.modelMapper.map(truck,TruckServiceModel.class)).collect(Collectors.toList());
    }


}

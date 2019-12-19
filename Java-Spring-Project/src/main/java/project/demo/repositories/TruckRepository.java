package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Truck;
import project.demo.domain.entities.enums.Status;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck,String> {

    List<Truck> findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(String brand, String model, Status status, BigDecimal price, Integer millage);

    List<Truck> getAllBy();

    List<Truck> getAllByManufacturerAndModel(String manufacturer,String model);

    Truck getById(String id);

    @Override
    void delete(Truck truck);

    List<Truck> findAllByUserId(String id);



}

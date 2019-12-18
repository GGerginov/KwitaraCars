package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Bus;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,String> {


    List<Bus> findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(String brand, String model, Status status, BigDecimal price, Integer millage);

    List<Bus> getAllBy();

    List<Bus> getAllByManufacturerAndModel(String manufacturer,String model);

    Bus getById(String id);

    @Override
    void delete(Bus bus);

    List<Bus> findAllByUserId(String id);
}

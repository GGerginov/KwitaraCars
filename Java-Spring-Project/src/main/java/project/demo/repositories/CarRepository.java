package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {

    List<Car> findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan(String brand, String model, Status status, BigDecimal price, Integer millage);

    List<Car> getAllBy();

    List<Car> getAllByManufacturerAndModel(String manufacturer,String model);

    Car getById(String id);

   void deleteCarById(String id);

   void deleteDistinctById(String id);

    List<Car> findAllByUserId(String id);
}

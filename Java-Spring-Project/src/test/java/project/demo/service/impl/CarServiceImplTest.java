package project.demo.service.impl;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.CarRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    List<Car> cars;
    CarRepository carRepository;
    Car car1;
    Car car2;

    @Before
    public void setUp() throws Exception {

        cars = new ArrayList<>();

        carRepository = Mockito.mock(CarRepository.class);

        Mockito.when(carRepository.getAllBy()).thenReturn(cars);

        car1 = new Car();
        car1.setManufacturer("BMW");
        car1.setModel("320");
        car1.setStatus(Status.USED);
        car1.setPrice(BigDecimal.valueOf(5000));
        car1.setMillage(50000);

        car2 = new Car();
        car2.setManufacturer("Mercedes");
        car2.setModel("S63");
        car2.setStatus(Status.NEW);
        car2.setPrice(BigDecimal.valueOf(500000));
        car2.setMillage(50000);
    }

    @Test
    public void findAllByManufacturerAndModelAndStatusAndPriceAndMillage() {

        this.cars.add(car1);

        List<Car> bmw = carRepository.findAllByManufacturerAndModelAndStatusAndPriceIsLessThanAndMillageLessThan
                ("BMW", "320", Status.USED, BigDecimal.valueOf(5000000), 500000);

        assertEquals(cars,bmw);
    }

    @Test
    public void publish() {
    }

    @Test
    public void getAllBy() {
    }

    @Test
    public void getAllByManufacturerAndModel() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAllByUserId() {
    }
}

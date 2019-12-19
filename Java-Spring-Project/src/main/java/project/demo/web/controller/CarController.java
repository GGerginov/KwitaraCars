package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.domain.entities.enums.Status;
import project.demo.service.CarService;
import project.demo.service.CloudinaryService;
import project.demo.service.UserService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.UserServiceModel;
import project.demo.validation.CarValidationService;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cars")

public class CarController extends BaseController {

    private ModelMapper modelMapper;

    private CarService carService;

    private UserService userService;

    private CarValidationService carValidationService;

    private CloudinaryService cloudinaryService;

    @Autowired
    public CarController(ModelMapper modelMapper, CarService carService, UserService userService, CarValidationService carValidationService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.userService = userService;
        this.carValidationService = carValidationService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create")
    public ModelAndView createCar(Principal principal) {

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/submit");
        modelAndView.addObject(userServiceModel);

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView crateSale(@ModelAttribute SaleCreateBindingModel model,Principal principal) {


        CarServiceModel carServiceModel = this.modelMapper.map(model, CarServiceModel.class);

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        if (this.carValidationService.isValid(carServiceModel)) {
            carServiceModel.setUser(userServiceModel);
            try {
                carServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.carService.publish(carServiceModel);

        return redirect("/home");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm() {

        return super.view("cars/search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars/search");

        if (model.getManufacturer().equals("") && model.getModel().equals("") && model.getMillage() == null && model.getPrice() == null && model.getLocation().equalsIgnoreCase("Any Location") && model.getStatus().equals("Any status")) {

            List<CarServiceModel> cars = this.carService.getAllBy();

            modelAndView.addObject(cars);
        }
        else if (model.getMillage() == null && model.getPrice() == null && model.getLocation().equalsIgnoreCase("Any Location") && model.getStatus().equals("Any status")){
            List<CarServiceModel> cars = this.carService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

            modelAndView.addObject(cars);
        }
        else {
            List<CarServiceModel> carServiceModels = this.carService
                    .findAllByManufacturerAndModelAndStatusAndPriceAndMillage
                            (model.getManufacturer(), model.getModel(), Status.valueOf(model.getStatus()), model.getPrice(), model.getMillage());

            modelAndView.addObject(carServiceModels);
        }



        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView();

        CarServiceModel car = this.carService.getById(id);

        modelAndView.addObject(car);
        modelAndView.setViewName("cars/carShow");

        return modelAndView;
    }



    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("cars/car-delete");

        CarServiceModel carServiceModel = this.carService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable String id) {


        this.carService.delete(id);

        return super.redirect("/home");
    }

    @GetMapping("/my-vehicles")
    public ModelAndView listAll(Principal principal){

        ModelAndView modelAndView = new ModelAndView();

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        List<CarServiceModel> allByUserId = this.carService.findAllByUserId(userServiceModel.getId());
        modelAndView.addObject(allByUserId);
        modelAndView.addObject(userServiceModel);
        modelAndView.setViewName("/cars/my-vehiculs");

        return modelAndView;
    }


}

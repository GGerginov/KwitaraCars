package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.domain.entities.enums.Status;
import project.demo.service.BusService;
import project.demo.service.CloudinaryService;
import project.demo.service.UserService;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.UserServiceModel;
import project.demo.validation.BusValidationService;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController extends BaseController {

    private ModelMapper modelMapper;

    private BusService busService;

    private UserService userService;

    private BusValidationService busValidationService;

    private CloudinaryService cloudinaryService;

    @Autowired
    public BusController(ModelMapper modelMapper, BusService busService, UserService userService, BusValidationService busValidationService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.busService = busService;
        this.userService = userService;
        this.busValidationService = busValidationService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create")
    public ModelAndView createCar(Principal principal) {

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buses/submit");
        modelAndView.addObject(userServiceModel);

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView crateSale(@ModelAttribute SaleCreateBindingModel model,Principal principal) {


        BusServiceModel busServiceModel = this.modelMapper.map(model, BusServiceModel.class);

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        if (this.busValidationService.isValid(busServiceModel)) {
            busServiceModel.setUser(userServiceModel);
            try {
                busServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.busService.publish(busServiceModel);

        return redirect("/home");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm() {

        return super.view("buses/search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buses/search");

        if (model.getManufacturer().equals("") && model.getModel().equals("") && model.getMillage() == null && model.getPrice() == null && model.getLocation().equalsIgnoreCase("Any Location") && model.getStatus().equalsIgnoreCase("Any status")) {

            List<BusServiceModel> cars = this.busService.getAllBy();

            modelAndView.addObject(cars);
        }
        else if (model.getMillage() == null && model.getPrice() == null && model.getLocation().equalsIgnoreCase("Any Location") && model.getStatus().equals("Any status")){
            List<BusServiceModel> cars = this.busService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

            modelAndView.addObject(cars);
        }
        else {
            List<BusServiceModel> carServiceModels = this.busService
                    .findAllByManufacturerAndModelAndStatusAndPriceAndMillage
                            (model.getManufacturer(), model.getModel(), Status.valueOf(model.getStatus()), model.getPrice(), model.getMillage());

            modelAndView.addObject(carServiceModels);
        }


        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView();

        BusServiceModel car = this.busService.getById(id);

        modelAndView.addObject(car);
        modelAndView.setViewName("buses/busShow");

        return modelAndView;
    }


    @GetMapping("/my-buses")
    public ModelAndView listAll(Principal principal){

        ModelAndView modelAndView = new ModelAndView();

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        List<BusServiceModel> allByUserId = this.busService.findAllByUserId(userServiceModel.getId());
        modelAndView.addObject(allByUserId);
        modelAndView.addObject(userServiceModel);
        modelAndView.setViewName("/buses/my-vehiculs");

        return modelAndView;
    }

}

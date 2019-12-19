package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.domain.entities.enums.Status;
import project.demo.service.*;
import project.demo.service.models.TruckServiceModel;
import project.demo.service.models.UserServiceModel;
import project.demo.validation.TruckValidationService;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/trucks")
public class TruckController extends BaseController {

    private ModelMapper modelMapper;

    private TruckService truckService;

    private UserService userService;

    private TruckValidationService truckValidationService;

    private CloudinaryService cloudinaryService;

    @Autowired
    public TruckController(ModelMapper modelMapper, TruckService truckService, UserService userService, TruckValidationService truckValidationService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.truckService = truckService;
        this.userService = userService;
        this.truckValidationService = truckValidationService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create")
    public ModelAndView createCar(Principal principal) {

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trucks/submit");

        modelAndView.addObject(userServiceModel);

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView crateSale(@ModelAttribute SaleCreateBindingModel model,Principal principal) {


        TruckServiceModel truckServiceModel = this.modelMapper.map(model, TruckServiceModel.class);

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        if (this.truckValidationService.isValid(truckServiceModel)) {
            truckServiceModel.setUser(userServiceModel);
            try {
                truckServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.truckService.publish(truckServiceModel);

        return redirect("/home");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm() {

        return super.view("trucks/search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trucks/search");


        List<TruckServiceModel> truckServiceModels = this.truckService
                .findAllByManufacturerAndModelAndStatusAndPriceAndMillage
                        (model.getManufacturer(), model.getModel(), Status.valueOf(model.getStatus()), model.getPrice(), model.getMillage());

        modelAndView.addObject(truckServiceModels);


        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id) {

        ModelAndView modelAndView = new ModelAndView();

        TruckServiceModel truck = this.truckService.getById(id);

        modelAndView.addObject(truck);
        modelAndView.setViewName("trucks/truckShow");

        return modelAndView;
    }


    @GetMapping("/my-vehicles")
    public ModelAndView listAll(Principal principal){

        ModelAndView modelAndView = new ModelAndView();

        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());

        List<TruckServiceModel> allByUserId = this.truckService.findAllByUserId(userServiceModel.getId());
        modelAndView.addObject(allByUserId);
        modelAndView.addObject(userServiceModel);
        modelAndView.setViewName("trucks/my-vehiculs");

        return modelAndView;
    }

}

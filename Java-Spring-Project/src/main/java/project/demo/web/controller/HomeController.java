package project.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.CarService;
import project.demo.service.models.CarServiceModel;
import project.demo.web.controller.base.BaseController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {

    private final CarService carService;

    @Autowired
    public HomeController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/")
    public ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView();

        List<CarServiceModel> carServiceModels = new ArrayList<>();

        carServiceModels.add(this.carService.getAllBy().get(0));
        carServiceModels.add(this.carService.getAllBy().get(1));
        carServiceModels.add(this.carService.getAllBy().get(2));

        modelAndView.addObject(carServiceModels);


        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/contacts")
    public ModelAndView contacts(){

        return super.view("contacts");
    }

    @GetMapping("/home")
    public ModelAndView home(){

        ModelAndView modelAndView = new ModelAndView();

        List<CarServiceModel> carServiceModels = new ArrayList<>();

        carServiceModels.add(this.carService.getAllBy().get(0));
        carServiceModels.add(this.carService.getAllBy().get(1));
        carServiceModels.add(this.carService.getAllBy().get(2));

        modelAndView.addObject(carServiceModels);


        modelAndView.setViewName("home");
        return modelAndView;
    }
}

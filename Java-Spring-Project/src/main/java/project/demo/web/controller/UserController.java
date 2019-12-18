package project.demo.web.controller;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.CloudinaryService;
import project.demo.service.UserService;
import project.demo.service.models.RoleServiceModel;
import project.demo.service.models.UserServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.users.UserEditBindingModel;
import project.demo.web.models.users.UserProfileViewModel;
import project.demo.web.models.users.UserRegisterBindingModel;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper,CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register() {
        return super.view("users/register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("users/register");
        }


        UserServiceModel userServiceModel = this.modelMapper.map(model,UserServiceModel.class);

        try {
            userServiceModel.setProfilePictureUrl(this.cloudinaryService.uploadImage(model.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.userService.registerUser(userServiceModel);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("users/login");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal{

        ModelAndView modelAndView = new ModelAndView();

        modelAndView
                .addObject("model", this.modelMapper
                        .map(this.userService.findUserByUserName(principal.getName()), UserServiceModel.class));

        modelAndView.setViewName("users/profile");
        return modelAndView;
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));

        return super.view("users/edit-profile", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel model){
        if (!model.getPassword().equals(model.getConfirmPassword())){
            return super.view("users/edit-profile");
        }

        this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());

        return super.redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView showAllUsers(ModelAndView modelAndView){
        List<UserServiceModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());

        Map<String, Set<RoleServiceModel>> userAndAuthorities = new HashMap<>();
        users.forEach(u -> userAndAuthorities.put(u.getId(), u.getAuthorities()));

        modelAndView.addObject("users", users);
        modelAndView.addObject("usersAndAuths", userAndAuthorities);
        return super.view("users/all-users", modelAndView);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteUser(@PathVariable String id, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findUserById(id);

        modelAndView.addObject("user", userServiceModel);
        modelAndView.addObject("userId", id);

        return super.view("users/delete-user", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteUserConfirm(@PathVariable String id) {
        this.userService.deleteUser(id);

        return super.redirect("/users/all");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdminRole(@PathVariable String id) {
        this.userService.makeAdmin(id);

        return super.redirect("/users/all");
    }

    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView setUserRole(@PathVariable String id) {
        this.userService.makeUser(id);

        return super.redirect("/users/all");
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
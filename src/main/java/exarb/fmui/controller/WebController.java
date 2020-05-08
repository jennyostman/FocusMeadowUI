package exarb.fmui.controller;

import exarb.fmui.client.LogInUserClient;
import exarb.fmui.client.RegisteredUserClient;
import exarb.fmui.exception.RegistrationException;
import exarb.fmui.model.*;
import exarb.fmui.service.FlowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
public class WebController {

    private final LogInUserClient logInUserClient;
    private final RegisteredUserClient RegisterUserClient;
    private final FlowerService flowerService;

    public WebController(LogInUserClient logInUserClient, RegisteredUserClient registerUserClient, FlowerService flowerService) {
        this.logInUserClient = logInUserClient;
        this.RegisterUserClient = registerUserClient;
        this.flowerService = flowerService;
    }

    @GetMapping("/focusMeadow")
    public String focusMeadow(Model model) {

        //TODO get all below from gamelogic
        FlowerWeb sunflower = new FlowerWeb("images/sunflower.jpg", "Sunflower", FlowerType.SUNFLOWER);
        FlowerWeb pansy = new FlowerWeb("images/pansy.jpg", "Pansy", FlowerType.PANSY);
        FlowerWeb grass = new FlowerWeb("images/grass.jpg", "Grass", FlowerType.GRASS);

        model.addAttribute("meadowFlowersList", flowerService.getMeadowFlowers());

        TimerWeb workTimer = new TimerWeb("userid", 25, true, null, false);
        TimerWeb pauseTimer = new TimerWeb("userid", 5, false, null, false);
        model.addAttribute("workTimer", workTimer);
        model.addAttribute("pauseTimer", pauseTimer);

        //TODO get from gamification
        List<FlowerWeb> shopFlowers = new ArrayList<>();
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        shopFlowers.add(sunflower);
        shopFlowers.add(pansy);
        model.addAttribute("shopFlowers", shopFlowers);

        return "focusMeadow";
    }

    @PostMapping("/saveSession")
    public String saveSession(@RequestBody TimerWeb result) {
        System.out.println("save");
        System.out.println("saveSession" + result.toString());
        System.out.println("user: " +result.getUserId());
        System.out.println("time: " + result.getTime());
        System.out.println("isWorkType: " + result.isWorkType());
        System.out.println("flower: " + result.getFlower().toString());
        System.out.println("interrupted: " + result.isInterrupted());
        return "saveSession";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        LoginWeb loginWeb = new LoginWeb();
        model.addAttribute("loginWeb", loginWeb);
        return "login";
    }

    @PostMapping("/login")
    // Vad ska ligga i ModelAttribute?!
    public String submitLoginForm(@ModelAttribute("user") LoginWeb loginWeb, Model model) {
        if (logInUserClient.logInByUsernameAndPassword(loginWeb) != null){
            return focusMeadow(model);
        }
        else {
            return "fel";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserWeb userWeb = new UserWeb();
        model.addAttribute("userWeb", userWeb);
        return "registration";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("userWeb") UserWeb userWeb, Model model) {
        if (RegisterUserClient.registerNewUser(userWeb) != null){
            System.out.println("Registreringen lyckades");
            LoginWeb loginWeb = new LoginWeb();
            model.addAttribute("loginWeb", loginWeb);
            return "login";
        }
        else {
            return "fel";
        }
    }

    @ExceptionHandler(RegistrationException.class)
    public ModelAndView handleException(RegistrationException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}

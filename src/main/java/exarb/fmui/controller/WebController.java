package exarb.fmui.controller;

import exarb.fmui.client.UserClient;
import exarb.fmui.client.RegisteredUserClient;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.enums.FlowerType;
import exarb.fmui.enums.SessionType;
import exarb.fmui.exception.RegistrationException;
import exarb.fmui.model.*;
import exarb.fmui.service.FlowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController {

    private final UserClient userClient;
    private final RegisteredUserClient RegisterUserClient;
    private final FlowerService flowerService;
    private UserGameData userGameData;

    public WebController(UserClient userClient, RegisteredUserClient registerUserClient, FlowerService flowerService) {
        this.userClient = userClient;
        this.RegisterUserClient = registerUserClient;
        this.flowerService = flowerService;
    }

    @GetMapping("/focusMeadow")
    public String focusMeadow(Model model) {
        model.addAttribute("userName", userGameData.getUserName());
        model.addAttribute("totalTime", userGameData.getEarnedHours() + "h " + userGameData.getEarnedMinutes() + "min");
        model.addAttribute("coins", userGameData.getCoins());
        model.addAttribute("meadow", flowerService.getMeadowFlowers(userGameData.getMeadow()));
        model.addAttribute("choosableFlowers", flowerService.getMeadowFlowers(userGameData.getChoosableFlowers()));

        //TODO get all below from gamelogic
        FlowerWeb sunflower = new FlowerWeb("images/sunflower.jpg", "Sunflower", FlowerType.SUNFLOWER);
        FlowerWeb pansy = new FlowerWeb("images/pansy.jpg", "Pansy", FlowerType.PANSY);
        FlowerWeb grass = new FlowerWeb("images/grass.jpg", "Grass", FlowerType.GRASS);



        TimerWeb workTimer = new TimerWeb("userid", 25, SessionType.WORK, null, false);
        TimerWeb pauseTimer = new TimerWeb("userid", 5, SessionType.PAUSE, null, false);
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
    public String saveSession(@RequestBody TimerWeb result, Model model) {
        System.out.println("save");
        System.out.println("saveSession" + result.toString());
        System.out.println("user: " + userGameData);
        result.setUserId(userGameData.getUserId());
        System.out.println("user: " + result.getUserId());
        System.out.println("time: " + result.getTime());
        System.out.println("isWorkType: " + result.getSessionType());
        System.out.println("flower: " + result.getFlowerToPlant());
        System.out.println("interrupted: " + result.isInterrupted());

        userGameData = userClient.saveTimerSession(result);

        System.out.println("user: " + userGameData);

        if (userGameData != null){
            return focusMeadow(model);
        }
        else {
            return "fel";
        }
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        LoginWeb loginWeb = new LoginWeb();
        model.addAttribute("loginWeb", loginWeb);
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("user") LoginWeb loginWeb, Model model) {
        userGameData = userClient.logInByUsernameAndPassword(loginWeb);
        if (userGameData != null){
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

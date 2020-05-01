package exarb.fmui.controller;

import exarb.fmui.client.LogInUserClient;
import exarb.fmui.client.RegisteredUserClient;
import exarb.fmui.exception.RegistrationException;
import exarb.fmui.model.LoginWeb;
import exarb.fmui.model.Timer;
import exarb.fmui.model.UserWeb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {

    private final LogInUserClient logInUserClient;
    private final RegisteredUserClient RegisterUserClient;

    public WebController(LogInUserClient logInUserClient, RegisteredUserClient registerUserClient) {
        this.logInUserClient = logInUserClient;
        RegisterUserClient = registerUserClient;
    }


    @GetMapping("/timer")
    public String timer(Model model) {
        Timer workTimer = new Timer(2, true, false);
        Timer pauseTimer = new Timer(1, false, false);
        model.addAttribute("workTimer", workTimer);
        model.addAttribute("pauseTimer", pauseTimer);
        return "timer";
    }

    @PostMapping("/saveSession")
    public String saveSession(@RequestBody Timer result) {
        System.out.println("saveSession" + result.toString());
        System.out.println("time: " + result.getTime());
        System.out.println("isWorkType: " + result.isWorkType());
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
    public String submitLoginForm(@ModelAttribute("user") LoginWeb loginWeb) {
        if (logInUserClient.logInByUsernameAndPassword(loginWeb) != null){
            return "focusMeadow";
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

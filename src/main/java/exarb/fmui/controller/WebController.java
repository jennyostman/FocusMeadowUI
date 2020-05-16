package exarb.fmui.controller;

import exarb.fmui.client.UserClient;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.enums.FlowerType;
import exarb.fmui.enums.SessionType;
import exarb.fmui.exception.RegistrationException;
import exarb.fmui.model.*;
import exarb.fmui.service.AchievementService;
import exarb.fmui.service.FlowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the webpage
 */
@Controller
public class WebController {

    private final UserClient userClient;
    private final FlowerService flowerService;
    private final AchievementService achievementService;
    private UserGameData userGameData;

    public WebController(UserClient userClient, FlowerService flowerService, AchievementService achievementService) {
        this.userClient = userClient;
        this.flowerService = flowerService;
        this.achievementService = achievementService;
    }

    /**
     * Adds all the data needed to show relevant info to the user and returns the main page.
     * @param model
     * @return focusMeadow
     */
    @GetMapping("/focusMeadow")
    public String focusMeadow(Model model) {
        model.addAttribute("userName", userGameData.getUserName());
        model.addAttribute("totalTime", userGameData.getEarnedHours() + "h " + userGameData.getEarnedMinutes() + "min");
        model.addAttribute("coins", userGameData.getCoins());
        model.addAttribute("meadow", flowerService.getMeadowFlowers(userGameData.getMeadow()));
        model.addAttribute("choosableFlowers", flowerService.getMeadowFlowers(userGameData.getChoosableFlowers()));
        model.addAttribute("shopFlowers", flowerService.getShopFlowers(userGameData.getChoosableFlowers()));

        //TODO get from gamification
        List<String> achivementList = new ArrayList<>();
        achivementList.add("hej");
        model.addAttribute("unearnedAchievements", achievementService.getUnearnedAchievements(achivementList));

        //TODO get all below from gamelogic
        TimerWeb workTimer = new TimerWeb("userid", 25, SessionType.WORK, null, false);
        TimerWeb pauseTimer = new TimerWeb("userid", 5, SessionType.PAUSE, null, false);
        model.addAttribute("workTimer", workTimer);
        model.addAttribute("pauseTimer", pauseTimer);

        return "focusMeadow";
    }

    /**
     * Takes a users timer session and sends it to gamelogic service to be saved.
     * Updates userGameData and returns the main page to update the view for the user
     * @param result - a timer session to be saved
     * @param model
     * @return focusMeadow
     */
    @PostMapping("/saveSession")
    public String saveSession(@RequestBody TimerWeb result, Model model) {
        result.setUserId(userGameData.getUserId());
        userGameData = userClient.saveTimerSession(result);

        if (userGameData != null){
            return focusMeadow(model);
        }
        else {
            return "fel";
        }
    }

    /**
     * Takes a flower the user wants to buy and sends it to gamelogic service to be saved.
     * Updates userGameData and returns the main page to update the view for the user
     * @param flowerType
     * @param model
     * @return focusMeadow
     */
    @PostMapping("/buyFlower")
    public String buyFlower(@RequestBody FlowerType flowerType, Model model) {
        userGameData = flowerService.buyFlower(flowerType, userGameData.getUserId());

        if (userGameData != null){
            return focusMeadow(model);
        }
        else {
            return "fel";
        }
    }

    /**
     * Sets the userGameData object to null and sends the user to the login page.
     * @param model
     * @return login
     */
    @GetMapping(value = "/logout")
    public String logout(Model model) {
        userGameData = null;
        LoginWeb loginWeb = new LoginWeb();
        model.addAttribute("loginWeb", loginWeb);
        return "login";
    }

    /**
     * Shows the user the login page and adds a loginWeb object
     * @param model
     * @return login
     */
    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        LoginWeb loginWeb = new LoginWeb();
        model.addAttribute("loginWeb", loginWeb);
        return "login";
    }

    /**
     * Takes the form submitted by the user and checks if the login is correct
     * @param loginWeb - the users login information
     * @param model
     * @return focusMeadow
     */
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

    /**
     * Shows the user the registration page and adds a userWeb object
     * @param model
     * @return registration
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserWeb userWeb = new UserWeb();
        model.addAttribute("userWeb", userWeb);
        return "registration";
    }

    /**
     * Takes the form submitted by the user and sends to user service to create a new user.
     * Sends the user back to the login page to login
     * @param userWeb - the users registration information
     * @param model
     * @return login
     */
    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("userWeb") UserWeb userWeb, Model model) {
        if (userClient.registerNewUser(userWeb) != null){
            System.out.println("Registreringen lyckades");
            LoginWeb loginWeb = new LoginWeb();
            model.addAttribute("loginWeb", loginWeb);
            return "login";
        }
        else {
            return "fel";
        }
    }

    /**
     * Handles an exception
     * @param ex
     * @return modelAndView
     */
    @ExceptionHandler(RegistrationException.class)
    public ModelAndView handleException(RegistrationException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}

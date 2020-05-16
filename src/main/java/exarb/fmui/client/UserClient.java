package exarb.fmui.client;

import exarb.fmui.client.dto.LoggedInUser;
import exarb.fmui.client.dto.RegisteredUser;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.model.LoginWeb;
import exarb.fmui.model.TimerWeb;
import exarb.fmui.model.UserWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *  * Client to get everything regarding the user from backend
 */
@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public UserClient(RestTemplate restTemplate, @Value("${userHost}") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**
     * Makes a rest post call to the user service with the users login data
     * @param loginWeb
     * @return userGameData
     */
    public UserGameData logInByUsernameAndPassword(LoginWeb loginWeb) {
        LoggedInUser loggedInUser = null;
        UserGameData userGameData = null;
        try {
            loggedInUser = restTemplate.postForEntity(userHost + "/users/login/", loginWeb, LoggedInUser.class).getBody();
            if (loggedInUser != null)
                userGameData = restTemplate.getForEntity(userHost + "/timers/game/" + loggedInUser.getUserId(), UserGameData.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return userGameData;
    }

    /**
     * Makes a rest post call to the user service with the users registration data
     * @param userWeb
     * @return
     */
    public RegisteredUser registerNewUser(UserWeb userWeb) {
        RegisteredUser registeredUser = null;
        try {
            registeredUser = restTemplate.postForEntity(userHost + "/users/registration",
                    userWeb,
                    RegisteredUser.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }
        return registeredUser;
    }

    /**
     * Saves a timer session to backend and gets an updated UserGameData object back
     * @param timerWeb - timer session to be saved
     * @return UserGameData - the updated data for the user
     */
    public UserGameData saveTimerSession(TimerWeb timerWeb) {
        UserGameData userGameData = null;
        try {
            userGameData = restTemplate.postForEntity(userHost + "/timers/timer/save",
                    timerWeb,
                    UserGameData.class,
                    timerWeb.getUserId()).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return userGameData;
    }
}

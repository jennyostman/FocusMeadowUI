package exarb.fmui.client;

import exarb.fmui.client.dto.LoggedInUser;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.model.LoginWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


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
}

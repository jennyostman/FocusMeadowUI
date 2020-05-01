package exarb.fmui.client;

import exarb.fmui.client.dto.LoggedInUser;
import exarb.fmui.model.LoginWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class LogInUserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public LogInUserClient(RestTemplate restTemplate, @Value("http://localhost:8082") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**
     * Makes a rest post call to the user service with the users login data
     * @param loginWeb
     * @return
     */
    public LoggedInUser logInByUsernameAndPassword(LoginWeb loginWeb) {
        LoggedInUser loggedInUser = null;
        try {
            loggedInUser = restTemplate.postForEntity(userHost + "/users/login/", loginWeb, LoggedInUser.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }
        return loggedInUser;
    }
}

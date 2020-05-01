package exarb.fmui.client;

import exarb.fmui.client.dto.RegisteredUser;
import exarb.fmui.model.UserWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RegisteredUserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public RegisteredUserClient(RestTemplate restTemplate, @Value("http://localhost:8082") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**
     * Makes a rest post call to the user service with the users registration data
     * @param userWeb
     * @return
     */
    public RegisteredUser registerNewUser(UserWeb userWeb) {
        RegisteredUser registeredUser = null;
        try {
            registeredUser = restTemplate.postForEntity(userHost + "/users/registration", userWeb, RegisteredUser.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }
        return registeredUser;
    }
}

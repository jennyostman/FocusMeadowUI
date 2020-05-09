package exarb.fmui.client;

import exarb.fmui.client.dto.UserGameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserGameDataClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public UserGameDataClient(RestTemplate restTemplate, @Value("${userHost}") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**
     * Makes a rest get call to the gamelogic service to get the users game data
     * @param userId
     * @return UserGameData
     */
    public UserGameData getUserGameData(String userId) {
        UserGameData userGameData = null;
        try {
            System.out.println("gamedata");
            userGameData = restTemplate.getForEntity(userHost + "/timers/game/5eb5594682ada75940d5c18c", UserGameData.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }
        System.out.println("user: " + userGameData);
        return userGameData;
    }
}

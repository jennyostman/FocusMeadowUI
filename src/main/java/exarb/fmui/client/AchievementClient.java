package exarb.fmui.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exarb.fmui.enums.FlowerType;
import exarb.fmui.model.AchievementWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Client to get everything regarding achievements from backend
 */
@Component
public class AchievementClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public AchievementClient(RestTemplate restTemplate, @Value("${userHost}") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**
     * Gets all the achievements in the database and returns them as a HashMap
     * @return Map<String, AchievementWeb>
     */
    public Map<String, AchievementWeb> getAllAchievementsMap() {
        Map<String, AchievementWeb> allAchievementsMap = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = restTemplate.getForEntity(userHost + "/achievements/all", JsonNode.class).getBody();
            allAchievementsMap = mapper.convertValue(jsonNode, new TypeReference<Map<String, AchievementWeb>>(){});
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return allAchievementsMap;
    }

    public List<String> getUsersEarnedAchievements(String userId){
        List<String> achievedAchievements = new ArrayList<>();

        try {
            JsonNode jsonNode = restTemplate.getForEntity(userHost + "/userachievements/user/" + userId, JsonNode.class).getBody();
            int counter = 0;
            while (jsonNode.get("achievedAchievements").has(counter)) {
                achievedAchievements.add(jsonNode.get("achievedAchievements").get(counter).asText());
                counter++;
            }
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return achievedAchievements;
    }
}

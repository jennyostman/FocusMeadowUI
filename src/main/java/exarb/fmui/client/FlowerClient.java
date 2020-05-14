package exarb.fmui.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.enums.FlowerType;
import exarb.fmui.model.FlowerWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FlowerClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public FlowerClient(RestTemplate restTemplate, @Value("${userHost}") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    /**Gets all the flowers in the database and returns them as a HashMap
     *
     * @return Map<FlowerType, FlowerWeb>
     */
    public Map<FlowerType, FlowerWeb> getAllFlowersMap() {
        Map<FlowerType, FlowerWeb> allFlowersMap = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = restTemplate.getForEntity(userHost + "/flowers/all/", JsonNode.class).getBody();
            allFlowersMap = mapper.convertValue(jsonNode, new TypeReference<Map<FlowerType, FlowerWeb>>(){});
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return allFlowersMap;
    }

    /**
     * Adds the chosen flower to the users choosableFlowers List in the
     * database and returns the updated UserGameData object
     * @param flowerType
     * @param userId
     * @return UserGameData
     */
    public UserGameData buyFlower(FlowerType flowerType, String userId) {
        UserGameData userGameData = null;
        try {
            userGameData = restTemplate.postForEntity(userHost + "/flowers/buy/" + flowerType + "/" + userId, null, UserGameData.class).getBody();
        }
        catch (Exception e){
            System.out.println("exception: " + e);
        }

        return userGameData;
    }
}

package exarb.fmui.client.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import exarb.fmui.client.dto.UserGameData;
import exarb.fmui.enums.FlowerType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserGameDataDeserializer extends JsonDeserializer<UserGameData> {

    /**
     * Deserializes a UserGameData object, and allows to create a new object
     * containing only the necessary variables.
     * @param jsonParser
     * @param deserializationContext
     * @return UserGameData
     * @throws IOException
     */
    @Override
    public UserGameData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        JsonNode meadowNode = node.get("meadow");
        List<FlowerType> meadow = new ArrayList<>();
        int counter = 0;
        while (meadowNode.has(counter)) {
            meadow.add(FlowerType.valueOf(meadowNode.get(counter).asText()));
            counter++;
        }

        JsonNode choosableFlowersNode = node.get("choosableFlowers");
        List<FlowerType> choosableFlowers = new ArrayList<>();
        counter = 0;
        while (choosableFlowersNode.has(counter)) {
            choosableFlowers.add(FlowerType.valueOf(choosableFlowersNode.get(counter).asText()));
            counter++;
        }

        return new UserGameData(node.get("userId").asText(), node.get("userName").asText(), meadow , node.get("coins").asInt(),
                choosableFlowers, node.get("earnedHours").asInt(), node.get("earnedMinutes").asInt());
    }
}

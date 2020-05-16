package exarb.fmui.client.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import exarb.fmui.client.dto.LoggedInUser;

import java.io.IOException;


public class LogInUserDeserializer extends JsonDeserializer<LoggedInUser> {

    /**
     * Deserializes a User object, and allows to create a new object
     * containing only the necessary variables.
     * @param jsonParser
     * @param deserializationContext
     * @return LoggedInUser
     * @throws IOException, JsonProcessingException
     */
    @Override
    public LoggedInUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        return new LoggedInUser(node.get("id").asText(), node.get("userName").asText());
    }
}

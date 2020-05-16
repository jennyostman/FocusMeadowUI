package exarb.fmui.client.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import exarb.fmui.client.dto.RegisteredUser;

import java.io.IOException;


public class RegisteredUserDeserializer extends JsonDeserializer<RegisteredUser> {

    /**
     * Deserializes a User object, and allows to create a new object
     * containing only the necessary variables.
     * @param jsonParser
     * @param deserializationContext
     * @return RegisteredUser
     * @throws IOException
     */
    @Override
    public RegisteredUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        return new RegisteredUser(node.get("userName").asText());
    }


}

package exarb.fmui.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmui.client.deserializer.RegisteredUserDeserializer;


@JsonDeserialize(using = RegisteredUserDeserializer.class)
public class RegisteredUser {

    private String userName;

    public RegisteredUser(String userName) {
        this.userName = userName;
    }

    public RegisteredUser() {
        this.userName = null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userName='" + userName + '\'' +
                '}';
    }
}

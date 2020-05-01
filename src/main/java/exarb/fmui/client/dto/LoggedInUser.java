package exarb.fmui.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmui.client.LogInUserDeserializer;

@JsonDeserialize(using = LogInUserDeserializer.class)
public class LoggedInUser {

    private String userName;

    public LoggedInUser(String userName) {
        this.userName = userName;
    }

    public LoggedInUser() {
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
        return "LoggedInUser{" +
                "userName='" + userName + '\'' +
                '}';
    }
}

package exarb.fmui.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmui.client.LogInUserDeserializer;

@JsonDeserialize(using = LogInUserDeserializer.class)
public class LoggedInUser {

    private String userId;
    private String userName;

    public LoggedInUser(String userName) {
        this.userName = userName;
    }

    public LoggedInUser() {
        this.userName = null;
    }

    public LoggedInUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

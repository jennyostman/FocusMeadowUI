package exarb.fmui.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmui.client.deserializer.UserGameDataDeserializer;
import exarb.fmui.enums.FlowerType;

import java.util.List;

/**
 * Model containing only the information needed from backend's UserGameData
 */
@JsonDeserialize(using = UserGameDataDeserializer.class)
public class UserGameData {

    private String userId;
    private String userName;
    private List<FlowerType> meadow;
    private int coins;
    private List<FlowerType> choosableFlowers;
    private int earnedHours;
    private int earnedMinutes;

    public UserGameData(String userId, String userName, List<FlowerType> meadow, int coins, List<FlowerType> choosableFlowers, int earnedHours, int earnedMinutes) {
        this.userId = userId;
        this.userName = userName;
        this.meadow = meadow;
        this.coins = coins;
        this.choosableFlowers = choosableFlowers;
        this.earnedHours = earnedHours;
        this.earnedMinutes = earnedMinutes;
    }

    public UserGameData() { }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<FlowerType> getMeadow() {
        return meadow;
    }

    public int getCoins() {
        return coins;
    }

    public List<FlowerType> getChoosableFlowers() {
        return choosableFlowers;
    }

    public int getEarnedHours() {
        return earnedHours;
    }

    public int getEarnedMinutes() {
        return earnedMinutes;
    }

    @Override
    public String toString() {
        return "UserGameData{" +
                "userName='" + userName + '\'' +
                ", meadow=" + meadow +
                ", coins=" + coins +
                ", choosableFlowers=" + choosableFlowers +
                ", earnedHours=" + earnedHours +
                ", earnedMinutes=" + earnedMinutes +
                '}';
    }
}

package exarb.fmui.model;

import exarb.fmui.enums.FlowerType;
import exarb.fmui.enums.SessionType;

/**
 * Model for the timers containing the information the frontend needs
 */
public class TimerWeb {

    private String id = null;
    private String userId;
    private int time;
    private SessionType sessionType;
    private boolean interrupted;
    private FlowerType flowerToPlant;

   /* private String id;
    private String userId;
    private int time;
    private SessionType sessionType;
    private boolean interrupted;
    private FlowerType flowerToPlant;*/

    public TimerWeb() {
    }

    public TimerWeb(String userId, int time, SessionType sessionType, FlowerType flowerToPlant, boolean interrupted) {
        this.userId = userId;
        this.time = time;
        this.sessionType = sessionType;
        this.flowerToPlant = flowerToPlant;
        this.interrupted = interrupted;
    }

    public int getTime() {
        return time;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FlowerType getFlowerToPlant() {
        return flowerToPlant;
    }

    @Override
    public String toString() {
        return "TimerWeb{" +
                "userId='" + userId + '\'' +
                ", time=" + time +
                ", workType=" + sessionType +
                ", flower=" + flowerToPlant +
                ", interrupted=" + interrupted +
                '}';
    }
}

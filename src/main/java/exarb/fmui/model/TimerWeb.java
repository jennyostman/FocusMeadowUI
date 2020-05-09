package exarb.fmui.model;

public class TimerWeb {

    private String userId;
    private int time;
    private boolean workType;
    private FlowerType flower;
    private boolean interrupted;

    public TimerWeb() {
    }

    public TimerWeb(String userId, int time, boolean workType, FlowerType flower, boolean interrupted) {
        this.userId = userId;
        this.time = time;
        this.workType = workType;
        this.flower = flower;
        this.interrupted = interrupted;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isWorkType() {
        return workType;
    }

    public void setWorkType(boolean workType) {
        this.workType = workType;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    public String getUserId() {
        return userId;
    }

    public FlowerType getFlower() {
        return flower;
    }

    @Override
    public String toString() {
        return "Timer{" +
                "time=" + time +
                ", isWorkType=" + workType +
                ", interrupted=" + interrupted +
                '}';
    }
}

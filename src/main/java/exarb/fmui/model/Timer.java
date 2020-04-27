package exarb.fmui.model;

public class Timer {

    private int time;
    private boolean workType;
    private boolean interrupted;

    public Timer() {
    }

    public Timer(int time, boolean workType, boolean interrupted) {
        this.time = time;
        this.workType = workType;
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

    @Override
    public String toString() {
        return "Timer{" +
                "time=" + time +
                ", isWorkType=" + workType +
                ", interrupted=" + interrupted +
                '}';
    }
}

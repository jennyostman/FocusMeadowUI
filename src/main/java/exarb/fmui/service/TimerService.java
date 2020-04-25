package exarb.fmui.service;

import exarb.fmui.model.Timer;

public class TimerService {

    public void runTimer(Timer timer) {

        while (true) {
            timer.setTime(timer.getTime() - 1);
            System.out.println(timer.getTime());
            if(timer.getTime() <= 0) {
                break;
            }
            try {
                Thread.sleep(1000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

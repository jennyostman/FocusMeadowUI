package exarb.fmui.controller;

import exarb.fmui.model.Timer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @GetMapping("/timer")
    public String timer(Model model) {
        Timer workTimer = new Timer(2, true, false);
        Timer pauseTimer = new Timer(1, false, false);
        model.addAttribute("workTimer", workTimer);
        model.addAttribute("pauseTimer", pauseTimer);
        return "timer";
    }

    @PostMapping("/saveSession")
    public String saveSession(@RequestBody Timer result) {
        System.out.println("saveSession" + result.toString());
        System.out.println("time: " + result.getTime());
        System.out.println("isWorkType: " + result.isWorkType());
        System.out.println("interrupted: " + result.isInterrupted());
        return "saveSession";
    }
}

package com.spacex.controller.monitor;

import com.spacex.annotation.ExecuteTimeAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flask")
public class MonitorController {

    @Value("${space.flask.test:defaultValue}")
    private String systemValue;

    @ExecuteTimeAnnotation
    @RequestMapping(value = "monitor", method = RequestMethod.POST)
    public String monitor() {
        sleep(1000L);
        System.out.println(systemValue);
        return "done";
    }

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

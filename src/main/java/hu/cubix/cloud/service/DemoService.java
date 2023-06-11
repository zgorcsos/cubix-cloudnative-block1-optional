package hu.cubix.cloud.service;

import hu.cubix.cloud.model.DemoResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DemoService {

    public DemoResponse createDemoResponse(String message) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        return new DemoResponse(date, time, message);
    }

}

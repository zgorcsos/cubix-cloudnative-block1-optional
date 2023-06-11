package hu.cubix.cloud.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record DemoResponse(LocalDate date, LocalTime time, String message) {
}

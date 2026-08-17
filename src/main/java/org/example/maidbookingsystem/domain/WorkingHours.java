package org.example.maidbookingsystem.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHours(
    DayOfWeek day,
    LocalTime startTime,
    LocalTime endTime
) {}
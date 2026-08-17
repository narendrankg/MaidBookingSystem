package org.example.maidbookingsystem.api.maid.dto;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHoursRequest(
    @NotNull DayOfWeek day,
    @NotNull LocalTime startTime,
    @NotNull LocalTime endTime
) {}
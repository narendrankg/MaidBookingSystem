package org.example.maidbookingsystem.api.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public record RecurringBookingRequestDto(
    @NotNull UUID customerId,
    @NotNull UUID maidId,
    @NotNull Instant firstStartAt,
    @NotNull @Positive Duration duration,
    @NotEmpty Set<DayOfWeek> days,
    @NotNull LocalTime time,
    @NotBlank String zoneId,
    @NotNull @Positive BigDecimal amount,
    @NotBlank String currency
) {}
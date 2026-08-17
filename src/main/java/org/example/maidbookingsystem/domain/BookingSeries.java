package org.example.maidbookingsystem.domain;

import org.example.maidbookingsystem.application.booking.RecurrenceRule;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record BookingSeries(
        UUID id, UUID customerId, UUID maidId, RecurrenceRule recurrenceRule,
        Duration duration, Instant firstStartAt, boolean cancelled
) {}
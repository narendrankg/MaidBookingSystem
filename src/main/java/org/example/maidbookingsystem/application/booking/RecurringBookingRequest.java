package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.BookingSlot;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public record RecurringBookingRequest(
        UUID customerId, UUID maidId, Instant firstStartAt, Duration duration,
        RecurrenceRule rule
) implements BookingRequest {
    public BookingSlot firstSlot(Clock clock) {
        return new BookingSlot(firstStartAt, firstStartAt.plus(duration));
    }
    public Optional<RecurrenceRule> recurrenceRule() { return Optional.of(rule); }
}
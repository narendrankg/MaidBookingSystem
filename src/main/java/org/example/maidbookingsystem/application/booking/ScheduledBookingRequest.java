package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.BookingSlot;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public record ScheduledBookingRequest(
        UUID customerId, UUID maidId, Instant startAt, Duration duration
) implements BookingRequest {
    public BookingSlot firstSlot(Clock clock) {
        return new BookingSlot(startAt, startAt.plus(duration));
    }
    public Optional<RecurrenceRule> recurrenceRule() { return Optional.empty(); }
}
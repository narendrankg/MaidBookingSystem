package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.BookingSlot;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public record InstantBookingRequest(
        UUID customerId, UUID maidId, Duration duration
) implements BookingRequest {
    public BookingSlot firstSlot(Clock clock) {
        Instant start = clock.instant();
        return new BookingSlot(start, start.plus(duration));
    }
    public Optional<RecurrenceRule> recurrenceRule() { return Optional.empty(); }
}
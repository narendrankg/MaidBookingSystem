package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.BookingSlot;

import java.time.Clock;
import java.util.Optional;
import java.util.UUID;

public interface BookingRequest {
    UUID customerId();
    UUID maidId();
    BookingSlot firstSlot(Clock clock);
    Optional<RecurrenceRule> recurrenceRule();
}
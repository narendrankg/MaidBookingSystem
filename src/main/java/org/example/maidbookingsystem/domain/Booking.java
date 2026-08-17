package org.example.maidbookingsystem.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Booking(
    UUID id,
    UUID customerId,
    UUID maidId,
    UUID seriesId,
    Instant startAt,
    Instant endAt,
    BigDecimal amount,
    String currency,
    BookingStatus status
) {
    public BookingSlot slot() { return new BookingSlot(startAt, endAt); }
    public boolean blocksAvailability() {
        return status == BookingStatus.PENDING_PAYMENT || status == BookingStatus.CONFIRMED;
    }
}
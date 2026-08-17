package org.example.maidbookingsystem.api.booking.dto;

import org.example.maidbookingsystem.domain.Booking;
import org.example.maidbookingsystem.domain.BookingStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record BookingResponse(
        UUID id,
        UUID seriesId,
        BookingStatus status,
        Instant startAt,
        Instant endAt,
        BigDecimal amount,
        String currency
) {
    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.id(), booking.seriesId(), booking.status(),
                booking.startAt(), booking.endAt(), booking.amount(), booking.currency()
        );
    }
}
package org.example.maidbookingsystem.api.cancellation.dto;

import org.example.maidbookingsystem.application.cancellation.CancellationScope;
import org.example.maidbookingsystem.domain.Booking;
import org.example.maidbookingsystem.domain.BookingStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CancellationResponse(
    UUID bookingId,
    UUID seriesId,
    BookingStatus status,
    CancellationScope scope,
    BigDecimal refundAmount,
    String currency
) {
    public static CancellationResponse from(
        Booking booking,
        CancellationScope scope,
        BigDecimal refundAmount
    ) {
        return new CancellationResponse(
            booking.id(),
            booking.seriesId(),
            booking.status(),
            scope,
            refundAmount,
            booking.currency()
        );
    }
}
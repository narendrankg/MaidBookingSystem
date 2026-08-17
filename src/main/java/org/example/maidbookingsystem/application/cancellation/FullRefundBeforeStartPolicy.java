package org.example.maidbookingsystem.application.cancellation;

import org.example.maidbookingsystem.domain.Booking;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class FullRefundBeforeStartPolicy implements CancellationPolicy {
    public BigDecimal refundAmount(Booking booking, Instant cancelledAt) {
        return cancelledAt.isBefore(booking.startAt()) ? booking.amount() : BigDecimal.ZERO;
    }
}
package org.example.maidbookingsystem.application.cancellation;

import org.example.maidbookingsystem.domain.Booking;

import java.math.BigDecimal;
import java.time.Instant;

public interface CancellationPolicy {
    BigDecimal refundAmount(Booking booking, Instant cancelledAt);
}
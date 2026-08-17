package org.example.maidbookingsystem.domain;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Optional<Payment> findByBookingId(UUID bookingId);
    Payment save(Payment payment);
}
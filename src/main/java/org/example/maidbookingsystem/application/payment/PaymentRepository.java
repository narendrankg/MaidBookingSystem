package org.example.maidbookingsystem.application.payment;

import org.example.maidbookingsystem.domain.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findSuccessfulByBookingId(UUID bookingId);
}
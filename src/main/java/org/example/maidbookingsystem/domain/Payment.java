package org.example.maidbookingsystem.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Payment(
        UUID id, UUID bookingId, PaymentMethodType method, BigDecimal amount,
        String currency, String providerReference, PaymentStatus status
) {}
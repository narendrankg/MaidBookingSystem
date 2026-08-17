package org.example.maidbookingsystem.application.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record RefundRequest(
        UUID bookingId, String providerReference, BigDecimal amount, String currency
) {}
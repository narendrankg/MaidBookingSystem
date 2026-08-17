package org.example.maidbookingsystem.application.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record ChargeRequest(
        UUID bookingId, BigDecimal amount, String currency, String idempotencyKey
) {}
package org.example.maidbookingsystem.application.payment;

import org.example.maidbookingsystem.domain.PaymentMethodType;

public interface PaymentMethod {
    PaymentMethodType type();
    PaymentResult charge(ChargeRequest request);
    PaymentResult refund(RefundRequest request);
}
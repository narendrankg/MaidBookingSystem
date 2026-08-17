package org.example.maidbookingsystem.infrastructure.payment;

import org.example.maidbookingsystem.application.payment.ChargeRequest;
import org.example.maidbookingsystem.application.payment.PaymentResult;
import org.example.maidbookingsystem.application.payment.RefundRequest;
import org.example.maidbookingsystem.domain.PaymentGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakePaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(ChargeRequest request) {
        return PaymentResult.success("fake_" + UUID.randomUUID());
    }

    @Override
    public PaymentResult refund(RefundRequest request) {
        return PaymentResult.success("fake_refund_" + UUID.randomUUID());
    }
}
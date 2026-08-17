package org.example.maidbookingsystem.domain;

import org.example.maidbookingsystem.application.payment.ChargeRequest;
import org.example.maidbookingsystem.application.payment.PaymentResult;
import org.example.maidbookingsystem.application.payment.RefundRequest;

public interface PaymentGateway {
    PaymentResult charge(ChargeRequest request);
    PaymentResult refund(RefundRequest request);
}
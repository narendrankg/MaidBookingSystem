package org.example.maidbookingsystem.application.payment;

import org.example.maidbookingsystem.domain.PaymentMethodType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentMethodRegistry {
    private final Map<PaymentMethodType, PaymentMethod> methods;

    public PaymentMethodRegistry(List<PaymentMethod> methods) {
        this.methods = methods.stream().collect(Collectors.toMap(PaymentMethod::type, Function.identity()));
    }

    public PaymentMethod get(PaymentMethodType type) {
        PaymentMethod method = methods.get(type);
        if (method == null) throw new IllegalArgumentException("Unsupported payment method");
        return method;
    }
}
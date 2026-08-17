package org.example.maidbookingsystem.application.payment;

public record PaymentResult(boolean successful, String providerReference) {
    public static PaymentResult success(String ref) { return new PaymentResult(true, ref); }
    public static PaymentResult failure(String ref) { return new PaymentResult(false, ref); }
}
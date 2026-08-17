package org.example.maidbookingsystem.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.maidbookingsystem.domain.PaymentMethodType;
import org.example.maidbookingsystem.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payments", indexes = {
    @Index(name = "idx_payment_booking", columnList = "booking_id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    @Enumerated(EnumType.STRING)
    private PaymentMethodType method;

    private BigDecimal amount;
    private String currency;
    private String providerReference;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentEntity(UUID id, BookingEntity booking, PaymentMethodType method,
                         BigDecimal amount, String currency, String providerReference,
                         PaymentStatus status) {
        this.id = id;
        this.booking = booking;
        this.method = method;
        this.amount = amount;
        this.currency = currency;
        this.providerReference = providerReference;
        this.status = status;
    }

    public void markRefunded() {
        this.status = PaymentStatus.REFUNDED;
    }
}
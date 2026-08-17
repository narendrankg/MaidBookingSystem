package org.example.maidbookingsystem.infrastructure.persistence.mapper;


import org.example.maidbookingsystem.domain.Payment;
import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingEntity;
import org.example.maidbookingsystem.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(), entity.getBooking().getId(), entity.getMethod(),
                entity.getAmount(), entity.getCurrency(), entity.getProviderReference(),
                entity.getStatus()
        );
    }

    public PaymentEntity toEntity(Payment payment, BookingEntity booking) {
        return new PaymentEntity(
                payment.id(), booking, payment.method(), payment.amount(),
                payment.currency(), payment.providerReference(), payment.status()
        );
    }
}
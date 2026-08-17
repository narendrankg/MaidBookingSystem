package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import org.example.maidbookingsystem.domain.PaymentStatus;
import org.example.maidbookingsystem.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
    Optional<PaymentEntity> findFirstByBooking_IdAndStatus(
            UUID bookingId,
            PaymentStatus status
    );
}
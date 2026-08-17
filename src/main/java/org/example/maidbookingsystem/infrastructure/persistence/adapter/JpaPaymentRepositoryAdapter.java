package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.payment.PaymentRepository;
import org.example.maidbookingsystem.domain.Payment;
import org.example.maidbookingsystem.domain.PaymentStatus;
import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingEntity;
import org.example.maidbookingsystem.infrastructure.persistence.mapper.PaymentMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {
    private final SpringDataPaymentRepository repository;
    private final SpringDataBookingRepository bookingRepository;
    private final PaymentMapper mapper;

    @Override
    public Payment save(Payment payment) {
        BookingEntity booking = bookingRepository.getReferenceById(payment.bookingId());
        return mapper.toDomain(repository.save(mapper.toEntity(payment, booking)));
    }

    @Override
    public Optional<Payment> findSuccessfulByBookingId(UUID bookingId) {
        return repository.findFirstByBooking_IdAndStatus(
                bookingId,
                PaymentStatus.SUCCEEDED
        ).map(mapper::toDomain);
    }
}
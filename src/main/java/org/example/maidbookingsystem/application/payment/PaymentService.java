package org.example.maidbookingsystem.application.payment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.booking.BookingRepository;
import org.example.maidbookingsystem.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRegistry methods;

    @Transactional
    public Booking pay(UUID bookingId, PaymentMethodType methodType) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        PaymentResult result = methods.get(methodType).charge(new ChargeRequest(
            booking.id(), booking.amount(), booking.currency(), booking.id().toString()
        ));
        Payment payment = new Payment(UUID.randomUUID(), booking.id(), methodType,
            booking.amount(), booking.currency(), result.providerReference(),
            result.successful() ? PaymentStatus.SUCCEEDED : PaymentStatus.FAILED);
        paymentRepository.save(payment);

        BookingStatus status = result.successful() ? BookingStatus.CONFIRMED : BookingStatus.PAYMENT_FAILED;
        return bookingRepository.save(new Booking(booking.id(), booking.customerId(), booking.maidId(),
            booking.seriesId(), booking.startAt(), booking.endAt(), booking.amount(), booking.currency(), status));
    }
}

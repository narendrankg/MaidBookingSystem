package org.example.maidbookingsystem.application.cancellation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.booking.BookingRepository;
import org.example.maidbookingsystem.application.booking.BookingSeriesRepository;
import org.example.maidbookingsystem.application.payment.PaymentMethodRegistry;
import org.example.maidbookingsystem.application.payment.PaymentRepository;
import org.example.maidbookingsystem.application.payment.RefundRequest;
import org.example.maidbookingsystem.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancellationService {
    private final BookingRepository bookingRepository;
    private final BookingSeriesRepository seriesRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRegistry methods;
    private final CancellationPolicy cancellationPolicy;
    private final Clock clock;

    @Transactional
    public CancellationResult cancel(UUID bookingId, CancellationScope scope) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (scope == CancellationScope.WHOLE_SERIES && booking.seriesId() != null) {
            BookingSeries series = seriesRepository.findById(booking.seriesId())
                    .orElseThrow(() -> new IllegalArgumentException("Booking series not found"));

            seriesRepository.save(new BookingSeries(
                    series.id(), series.customerId(), series.maidId(),
                    series.recurrenceRule(), series.duration(),
                    series.firstStartAt(), true
            ));

            BigDecimal totalRefund = bookingRepository.findBySeriesId(series.id()).stream()
                    .map(this::cancelOne)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return new CancellationResult(
                    cancelledBooking(booking),
                    totalRefund
            );
        }

        return new CancellationResult(cancelledBooking(booking), cancelOne(booking));
    }

    private BigDecimal cancelOne(Booking booking) {
        if (booking.status() == BookingStatus.CANCELLED) {
            return BigDecimal.ZERO;
        }

        bookingRepository.save(new Booking(
                booking.id(), booking.customerId(), booking.maidId(), booking.seriesId(),
                booking.startAt(), booking.endAt(), booking.amount(), booking.currency(),
                BookingStatus.CANCELLED
        ));

        return paymentRepository.findSuccessfulByBookingId(booking.id())
                .map(payment -> refund(booking, payment))
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal refund(Booking booking, Payment payment) {
        BigDecimal refundAmount = cancellationPolicy.refundAmount(booking, clock.instant());
        if (refundAmount.signum() <= 0) {
            return BigDecimal.ZERO;
        }

        methods.get(payment.method()).refund(new RefundRequest(
                booking.id(), payment.providerReference(), refundAmount, booking.currency()
        ));

        paymentRepository.save(new Payment(
                payment.id(), payment.bookingId(), payment.method(), payment.amount(),
                payment.currency(), payment.providerReference(), PaymentStatus.REFUNDED
        ));

        return refundAmount;
    }

    private Booking cancelledBooking(Booking booking) {
        return new Booking(
                booking.id(), booking.customerId(), booking.maidId(), booking.seriesId(),
                booking.startAt(), booking.endAt(), booking.amount(), booking.currency(),
                BookingStatus.CANCELLED
        );
    }

    public record CancellationResult(
            Booking booking,
            BigDecimal refundAmount
    ) {}
}

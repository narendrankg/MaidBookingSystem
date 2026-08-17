package org.example.maidbookingsystem.application.booking;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.domain.Booking;
import org.example.maidbookingsystem.domain.BookingSeries;
import org.example.maidbookingsystem.domain.BookingSlot;
import org.example.maidbookingsystem.domain.BookingStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private static final Duration RECURRENCE_HORIZON = Duration.ofDays(90);

    private final AvailabilityService availabilityService;
    private final BookingRepository bookingRepository;
    private final BookingSeriesRepository seriesRepository;
    private final Clock clock;

    @Transactional
    public Booking create(BookingRequest request, BigDecimal amount, String currency) {
        BookingSlot firstSlot = request.firstSlot(clock);
        availabilityService.requireAvailable(request.maidId(), firstSlot);

        UUID seriesId = request.recurrenceRule().map(rule -> {
            Instant horizon = firstSlot.startAt().plus(RECURRENCE_HORIZON);
            for (Instant start : rule.occurrences(firstSlot.startAt(), horizon)) {
                availabilityService.requireAvailable(
                    request.maidId(), new BookingSlot(start, start.plus(Duration.between(firstSlot.startAt(), firstSlot.endAt())))
                );
            }
            BookingSeries series = new BookingSeries(
                UUID.randomUUID(), request.customerId(), request.maidId(), rule,
                    Duration.between(firstSlot.startAt(), firstSlot.endAt()), firstSlot.startAt(), false
            );
            return seriesRepository.save(series).id();
        }).orElse(null);

        Booking booking = new Booking(
            UUID.randomUUID(), request.customerId(), request.maidId(), seriesId,
            firstSlot.startAt(), firstSlot.endAt(), amount, currency, BookingStatus.PENDING_PAYMENT
        );
        return bookingRepository.save(booking);
    }
}

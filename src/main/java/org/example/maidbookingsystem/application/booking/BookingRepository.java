package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.Booking;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    Booking save(Booking booking);
    Optional<Booking> findById(UUID bookingId);
    boolean existsOverlappingActiveBooking(UUID maidId, Instant startAt, Instant endAt);
    List<Booking> findBySeriesId(UUID seriesId);
}
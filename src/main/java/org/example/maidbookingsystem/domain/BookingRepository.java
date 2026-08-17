package org.example.maidbookingsystem.domain;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    Optional<Booking> findById(UUID bookingId);
    Booking save(Booking booking);
}
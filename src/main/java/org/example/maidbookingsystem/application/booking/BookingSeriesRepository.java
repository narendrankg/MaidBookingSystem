package org.example.maidbookingsystem.application.booking;

import org.example.maidbookingsystem.domain.BookingSeries;

import java.util.Optional;
import java.util.UUID;

public interface BookingSeriesRepository {
    BookingSeries save(BookingSeries series);
    Optional<BookingSeries> findById(UUID seriesId);
}
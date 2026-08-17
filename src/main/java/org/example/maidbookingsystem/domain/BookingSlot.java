package org.example.maidbookingsystem.domain;

import java.time.Instant;

public record BookingSlot(Instant startAt, Instant endAt) {
    public BookingSlot {
        if (!endAt.isAfter(startAt)) throw new IllegalArgumentException("End must be after start");
    }
}
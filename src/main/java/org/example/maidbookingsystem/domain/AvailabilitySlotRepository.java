package org.example.maidbookingsystem.domain;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilitySlotRepository {
    Optional<AvailabilitySlot> findByMaidIdAndStartAt(UUID maidId, Instant startAt);
    AvailabilitySlot save(AvailabilitySlot slot);
}
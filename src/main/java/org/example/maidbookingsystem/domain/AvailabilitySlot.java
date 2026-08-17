package org.example.maidbookingsystem.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class AvailabilitySlot {
    private final UUID id;
    private final UUID maidId;
    private final Instant startAt;
    private final Instant endAt;
    private SlotStatus status;
    private long version;

    public AvailabilitySlot(UUID id, UUID maidId, Instant startAt, Instant endAt) {
        if (!endAt.isAfter(startAt)) throw new IllegalArgumentException("End must be after start");
        this.id = Objects.requireNonNull(id);
        this.maidId = Objects.requireNonNull(maidId);
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = SlotStatus.AVAILABLE;
    }

    public void reserve() {
        if (status != SlotStatus.AVAILABLE) throw new SlotUnavailableException();
        status = SlotStatus.RESERVED;
    }

    public void release() {
        if (status != SlotStatus.RESERVED) throw new IllegalStateException("Slot is not reserved");
        status = SlotStatus.AVAILABLE;
    }

    public UUID id() { return id; }
    public UUID maidId() { return maidId; }
    public Instant startAt() { return startAt; }
    public Instant endAt() { return endAt; }
    public SlotStatus status() { return status; }
    public long version() { return version; }

    public static AvailabilitySlot rehydrate(
            UUID id,
            UUID maidId,
            Instant startAt,
            Instant endAt,
            SlotStatus status,
            long version
    ) {
        AvailabilitySlot slot = new AvailabilitySlot(id, maidId, startAt, endAt);
        slot.status = Objects.requireNonNull(status);
        slot.version = version;
        return slot;
    }
}
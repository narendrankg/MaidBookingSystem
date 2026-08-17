package org.example.maidbookingsystem.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.maidbookingsystem.domain.RecurrenceType;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "booking_series")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingSeriesEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private UUID maidId;
    private Instant firstStartAt;
    private long durationMinutes;
    private boolean cancelled;

    @Enumerated(EnumType.STRING)
    private RecurrenceType recurrenceType;

    @ElementCollection
    @CollectionTable(name = "booking_series_days", joinColumns = @JoinColumn(name = "series_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private Set<DayOfWeek> days = new HashSet<>();

    private LocalTime recurrenceTime;
    private String zoneId;

    public BookingSeriesEntity(UUID id, UUID customerId, UUID maidId, Instant firstStartAt,
                               Duration duration, Set<DayOfWeek> days,
                               LocalTime recurrenceTime, String zoneId) {
        this.id = id;
        this.customerId = customerId;
        this.maidId = maidId;
        this.firstStartAt = firstStartAt;
        this.durationMinutes = duration.toMinutes();
        this.recurrenceType = RecurrenceType.WEEKLY;
        this.days.addAll(days);
        this.recurrenceTime = recurrenceTime;
        this.zoneId = zoneId;
    }

    public void cancel() {
        this.cancelled = true;
    }
}

package org.example.maidbookingsystem.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "maid_working_hours")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkingHourEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maid_id", nullable = false)
    private MaidEntity maid;

    @Enumerated(EnumType.STRING)
    @Column(name = "working_day", nullable = false)
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkingHourEntity(UUID id, DayOfWeek day, LocalTime startTime,
                             LocalTime endTime) {
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    void assignTo(MaidEntity maid) {
        this.maid = maid;
    }
}
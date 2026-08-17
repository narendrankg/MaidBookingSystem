package org.example.maidbookingsystem.infrastructure.persistence.mapper;

import org.example.maidbookingsystem.application.booking.RecurrenceRule;
import org.example.maidbookingsystem.application.booking.WeeklyRecurrenceRule;
import org.example.maidbookingsystem.domain.BookingSeries;
import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingSeriesEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZoneId;
import java.util.Set;

@Component
public class BookingSeriesMapper {

    public BookingSeries toDomain(BookingSeriesEntity entity) {
        RecurrenceRule rule = new WeeklyRecurrenceRule(
            Set.copyOf(entity.getDays()),
            entity.getRecurrenceTime(),
            ZoneId.of(entity.getZoneId())
        );

        return new BookingSeries(
            entity.getId(),
            entity.getCustomerId(),
            entity.getMaidId(),
            rule,
            Duration.ofMinutes(entity.getDurationMinutes()),
            entity.getFirstStartAt(),
            entity.isCancelled()
        );
    }

    public BookingSeriesEntity toEntity(BookingSeries series) {
        if (!(series.recurrenceRule() instanceof WeeklyRecurrenceRule rule)) {
            throw new IllegalArgumentException("Only weekly recurrence is supported");
        }

        BookingSeriesEntity entity = new BookingSeriesEntity(
            series.id(),
            series.customerId(),
            series.maidId(),
            series.firstStartAt(),
            series.duration(),
            rule.days(),
            rule.time(),
            rule.zone().getId()
        );

        if (series.cancelled()) {
            entity.cancel();
        }
        return entity;
    }
}
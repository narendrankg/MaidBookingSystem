package org.example.maidbookingsystem.application.booking;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record WeeklyRecurrenceRule(
        Set<DayOfWeek> days, LocalTime time, ZoneId zone
) implements RecurrenceRule {
    public List<Instant> occurrences(Instant from, Instant until) {
        List<Instant> result = new ArrayList<>();
        for (LocalDate date = from.atZone(zone).toLocalDate();
             !date.isAfter(until.atZone(zone).toLocalDate());
             date = date.plusDays(1)) {
            Instant candidate = ZonedDateTime.of(date, time, zone).toInstant();
            if (days.contains(date.getDayOfWeek())
                    && !candidate.isBefore(from)
                    && candidate.isBefore(until)) {
                result.add(candidate);
            }
        }
        return result;
    }
}
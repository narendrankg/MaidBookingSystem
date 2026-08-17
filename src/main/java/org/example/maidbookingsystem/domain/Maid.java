package org.example.maidbookingsystem.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Maid(
        UUID id,
        String name,
        String locality,
        Set<ServiceType> services,
        Set<String> skills,
        BigDecimal hourlyPrice,
        Gender gender,
        List<WorkingHours> workingHours,
        BigDecimal rating
) {
    public Maid {
        services = Set.copyOf(services);
        skills = Set.copyOf(skills);
        workingHours = List.copyOf(workingHours);
    }

    public boolean worksDuring(BookingSlot slot) {
        LocalDateTime start = slot.startAt().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime end = slot.endAt().atZone(ZoneOffset.UTC).toLocalDateTime();

        if (!start.toLocalDate().equals(end.toLocalDate())) {
            return false;
        }

        return workingHours.stream().anyMatch(hours ->
                hours.day() == start.getDayOfWeek()
                        && !start.toLocalTime().isBefore(hours.startTime())
                        && !end.toLocalTime().isAfter(hours.endTime())
        );
    }
}
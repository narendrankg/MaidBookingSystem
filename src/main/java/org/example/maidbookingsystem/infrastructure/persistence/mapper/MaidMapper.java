package org.example.maidbookingsystem.infrastructure.persistence.mapper;

import org.example.maidbookingsystem.domain.Maid;
import org.example.maidbookingsystem.domain.WorkingHours;
import org.example.maidbookingsystem.infrastructure.persistence.entity.MaidEntity;
import org.example.maidbookingsystem.infrastructure.persistence.entity.WorkingHourEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class MaidMapper {
    public Maid toDomain(MaidEntity entity) {
        List<WorkingHours> windows = entity.getWorkingHours().stream()
                .map(w -> new WorkingHours(
                        w.getDay(), w.getStartTime(), w.getEndTime()
                ))
                .toList();

        return new Maid(
                entity.getId(), entity.getName(), entity.getLocality(),
                Set.copyOf(entity.getServices()), Set.copyOf(entity.getSkills()),
                entity.getHourlyPrice(), entity.getGender(), windows, entity.getRating()
        );
    }

    public MaidEntity toEntity(Maid maid) {
        MaidEntity entity = new MaidEntity(
                maid.id(), maid.name(), maid.locality(), maid.hourlyPrice(),
                maid.gender(), maid.services(), maid.skills()
        );
        maid.workingHours().forEach(window -> entity.addAvailabilityWindow(
                new WorkingHourEntity(
                        UUID.randomUUID(), window.day(), window.startTime(),
                        window.endTime()
                )
        ));
        return entity;
    }
}
package org.example.maidbookingsystem.application.maid;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.domain.Maid;
import org.example.maidbookingsystem.domain.WorkingHours;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaidService {
    private final MaidRepository maidRepository;

    public Maid onboard(OnboardMaidCommand command) {
        validateWorkingHours(command.workingHours());

        Maid maid = new Maid(
                UUID.randomUUID(),
                command.name(),
                command.locality(),
                command.services(),
                command.skills() == null ? Set.of() : command.skills(),
                command.hourlyPrice(),
                command.gender(),
                command.workingHours(),
                BigDecimal.ZERO
        );

        return maidRepository.save(maid);
    }

    private void validateWorkingHours(List<WorkingHours> workingHours) {
        for (WorkingHours hours : workingHours) {
            if (!hours.startTime().isBefore(hours.endTime())) {
                throw new IllegalArgumentException(
                        "Working-hours start time must be before end time"
                );
            }
        }
    }
}
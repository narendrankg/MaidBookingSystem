package org.example.maidbookingsystem.application.maid;

import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.ServiceType;
import org.example.maidbookingsystem.domain.WorkingHours;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record OnboardMaidCommand(
        String name,
        String locality,
        Set<ServiceType> services,
        Set<String> skills,
        BigDecimal hourlyPrice,
        Gender gender,
        List<WorkingHours> workingHours
) {}
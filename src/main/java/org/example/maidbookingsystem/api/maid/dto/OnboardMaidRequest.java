package org.example.maidbookingsystem.api.maid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.ServiceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record OnboardMaidRequest(
    @NotBlank String name,
    @NotBlank String locality,
    @NotEmpty Set<ServiceType> services,
    Set<String> skills,
    @NotNull @Positive BigDecimal hourlyPrice,
    @NotNull Gender gender,
    @NotEmpty List<WorkingHoursRequest> workingHours
) {}
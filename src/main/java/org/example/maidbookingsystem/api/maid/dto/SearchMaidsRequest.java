package org.example.maidbookingsystem.api.maid.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.ServiceType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record SearchMaidsRequest(
    @NotBlank String locality,
    @NotNull Instant startAt,
    @NotNull Instant endAt,
    Set<ServiceType> requiredServices,
    Set<String> requiredSkills,
    @DecimalMin("0.0") BigDecimal minimumRating,
    @Positive BigDecimal maximumHourlyPrice,
    Gender genderPreference
) {}
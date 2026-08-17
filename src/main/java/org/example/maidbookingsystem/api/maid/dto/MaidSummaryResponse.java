package org.example.maidbookingsystem.api.maid.dto;

import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.Maid;
import org.example.maidbookingsystem.domain.ServiceType;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record MaidSummaryResponse(
    UUID id,
    String name,
    String locality,
    Set<ServiceType> services,
    Set<String> skills,
    BigDecimal hourlyPrice,
    BigDecimal rating,
    Gender gender
) {
    public static MaidSummaryResponse from(Maid maid) {
        return new MaidSummaryResponse(
            maid.id(),
            maid.name(),
            maid.locality(),
            maid.services(),
            maid.skills(),
            maid.hourlyPrice(),
            maid.rating(),
            maid.gender()
        );
    }
}

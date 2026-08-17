package org.example.maidbookingsystem.application.maid;

import org.example.maidbookingsystem.domain.BookingSlot;
import org.example.maidbookingsystem.domain.Gender;
import org.example.maidbookingsystem.domain.ServiceType;

import java.math.BigDecimal;
import java.util.Set;

public record MaidSearchCriteria(
    String locality,
    BookingSlot requestedSlot,
    Set<ServiceType> requiredServices,
    Set<String> requiredSkills,
    BigDecimal minimumRating,
    BigDecimal maximumHourlyPrice,
    Gender genderPreference
) {}
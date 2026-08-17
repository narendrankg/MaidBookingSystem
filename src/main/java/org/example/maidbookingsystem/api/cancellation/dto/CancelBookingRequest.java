package org.example.maidbookingsystem.api.cancellation.dto;

import jakarta.validation.constraints.NotNull;
import org.example.maidbookingsystem.application.cancellation.CancellationScope;

public record CancelBookingRequest(
    @NotNull CancellationScope scope
) {}
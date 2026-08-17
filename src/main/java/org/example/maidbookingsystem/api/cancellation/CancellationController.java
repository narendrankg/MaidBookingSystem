package org.example.maidbookingsystem.api.cancellation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.api.cancellation.dto.CancelBookingRequest;
import org.example.maidbookingsystem.api.cancellation.dto.CancellationResponse;
import org.example.maidbookingsystem.application.cancellation.CancellationService;
import org.example.maidbookingsystem.domain.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class CancellationController {
    private final CancellationService cancellationService;

    @PostMapping("/{bookingId}/cancellation")
    public ResponseEntity<CancellationResponse> cancel(
            @PathVariable UUID bookingId,
            @Valid @RequestBody CancelBookingRequest request
    ) {
        CancellationService.CancellationResult result = cancellationService.cancel(bookingId, request.scope());

        return ResponseEntity.ok(CancellationResponse.from(
                result.booking(),
                request.scope(),
                result.refundAmount()
        ));
    }
}
package org.example.maidbookingsystem.api.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.api.booking.dto.BookingResponse;
import org.example.maidbookingsystem.api.booking.dto.RecurringBookingRequestDto;
import org.example.maidbookingsystem.application.booking.BookingService;
import org.example.maidbookingsystem.application.booking.RecurrenceRule;
import org.example.maidbookingsystem.application.booking.RecurringBookingRequest;
import org.example.maidbookingsystem.application.booking.WeeklyRecurrenceRule;
import org.example.maidbookingsystem.domain.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/recurring")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createRecurring(
            @Valid @RequestBody RecurringBookingRequestDto request
    ) {
        RecurrenceRule rule = new WeeklyRecurrenceRule(
                request.days(),
                request.time(),
                ZoneId.of(request.zoneId())
        );

        Booking booking = bookingService.create(
                new RecurringBookingRequest(
                        request.customerId(),
                        request.maidId(),
                        request.firstStartAt(),
                        request.duration(),
                        rule
                ),
                request.amount(),
                request.currency()
        );

        return BookingResponse.from(booking);
    }
}
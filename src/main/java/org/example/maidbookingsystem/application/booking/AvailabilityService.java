package org.example.maidbookingsystem.application.booking;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.maid.MaidRepository;
import org.example.maidbookingsystem.domain.BookingSlot;
import org.example.maidbookingsystem.domain.Maid;
import org.example.maidbookingsystem.domain.SlotUnavailableException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvailabilityService {
    private final MaidRepository maidRepository;
    private final BookingRepository bookingRepository;

    public void requireAvailable(UUID maidId, BookingSlot slot) {
        Maid maid = maidRepository.findById(maidId)
            .orElseThrow(() -> new IllegalArgumentException("Maid not found"));
        if (!maid.worksDuring(slot)) throw new SlotUnavailableException();
        if (bookingRepository.existsOverlappingActiveBooking(maidId, slot.startAt(), slot.endAt())) {
            throw new SlotUnavailableException();
        }
    }
}
package org.example.maidbookingsystem.application.maid;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.booking.BookingRepository;
import org.example.maidbookingsystem.domain.Maid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaidSearchService {
    private final MaidRepository maidRepository;
    private final BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    public List<Maid> search(MaidSearchCriteria criteria) {
        return maidRepository.search(criteria).stream()
            .filter(maid -> maid.worksDuring(criteria.requestedSlot()))
            .filter(maid -> !bookingRepository.existsOverlappingActiveBooking(
                maid.id(),
                criteria.requestedSlot().startAt(),
                criteria.requestedSlot().endAt()
            ))
            .toList();
    }
}
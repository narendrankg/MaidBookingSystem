package org.example.maidbookingsystem.api.maid;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.api.maid.dto.MaidSummaryResponse;
import org.example.maidbookingsystem.api.maid.dto.SearchMaidsRequest;
import org.example.maidbookingsystem.application.maid.MaidSearchCriteria;
import org.example.maidbookingsystem.application.maid.MaidSearchService;
import org.example.maidbookingsystem.domain.BookingSlot;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maids")
@RequiredArgsConstructor
public class MaidDiscoveryController {
    private final MaidSearchService maidSearchService;

    @PostMapping("/search")
    public List<MaidSummaryResponse> search(
        @Valid @RequestBody SearchMaidsRequest request
    ) {
        BookingSlot requestedSlot = new BookingSlot(
            request.startAt(),
            request.endAt()
        );

        MaidSearchCriteria criteria = new MaidSearchCriteria(
            request.locality(),
            requestedSlot,
            request.requiredServices(),
            request.requiredSkills(),
            request.minimumRating(),
            request.maximumHourlyPrice(),
            request.genderPreference()
        );

        return maidSearchService.search(criteria).stream()
            .map(MaidSummaryResponse::from)
            .toList();
    }
}
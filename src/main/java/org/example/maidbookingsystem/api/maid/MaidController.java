package org.example.maidbookingsystem.api.maid;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.api.maid.dto.MaidSummaryResponse;
import org.example.maidbookingsystem.api.maid.dto.OnboardMaidRequest;
import org.example.maidbookingsystem.application.maid.MaidService;
import org.example.maidbookingsystem.application.maid.OnboardMaidCommand;
import org.example.maidbookingsystem.domain.Maid;
import org.example.maidbookingsystem.domain.WorkingHours;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maids")
@RequiredArgsConstructor
public class MaidController {
    private final MaidService maidService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaidSummaryResponse onboard(
        @Valid @RequestBody OnboardMaidRequest request
    ) {
        Maid maid = maidService.onboard(new OnboardMaidCommand(
            request.name(),
            request.locality(),
            request.services(),
            request.skills(),
            request.hourlyPrice(),
            request.gender(),
                request.workingHours().stream()
                        .map(hours -> new WorkingHours(
                                hours.day(),
                                hours.startTime(),
                                hours.endTime()
                        ))
                        .toList()
        ));
        return MaidSummaryResponse.from(maid);
    }
}
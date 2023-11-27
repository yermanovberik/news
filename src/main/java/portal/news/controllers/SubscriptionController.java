package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import portal.news.dto.subscription.SubscriptionRequestDto;
import portal.news.dto.subscription.SubscriptionResponseDto;
import portal.news.services.SubscriptionService;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping(value = "/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionResponseDto getEventById(
            @PathVariable @Positive(message = "Id must be greater than zero") long id
    ) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponseDto createSubscription(
            @RequestBody @Valid SubscriptionRequestDto requestDto
    ) {
        return service.create(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(
            @PathVariable @Positive(message = "Id must be greater than zero") long id
    ) {
        service.delete(id);
    }

}

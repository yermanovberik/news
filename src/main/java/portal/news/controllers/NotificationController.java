package portal.news.controllers;
/*
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portal.news.producer.RabbitMQProducer;
import portal.news.services.NotificationService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final RabbitMQProducer rabbitMQProducer;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotificationResponseDto getNotificationById(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        return notificationService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponseDto createNotification(
            @RequestBody @Valid NotificationRequestDto requestDto
    ) {
        return notificationService.create(requestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotificationResponseDto updateCategory(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid NotificationRequestDto requestDto
    ) {
        return notificationService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        notificationService.delete(id);
    }

    @GetMapping("/send")
    public String sendNotification() {
        String userEmail = "yermanberik@gmail.com";
        String notificationText = "A new news item is available!";

        rabbitMQProducer.publishNews(userEmail, notificationText);

        return "Notification sent successfully!";
    }
}
*/
package portal.news.services.implementation;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import portal.news.dto.notification.NotificationRequestDto;
import portal.news.dto.notification.NotificationResponseDto;
import portal.news.models.Notification;
import portal.news.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Override
    public NotificationResponseDto getById(long id) {
        return null;
    }

    @Override
    public NotificationResponseDto create(NotificationRequestDto requestDto) {
        return null;
    }

    @Override
    public NotificationResponseDto update(long id, NotificationRequestDto requestDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Notification getEntityById(long id) {
        return null;
    }
}

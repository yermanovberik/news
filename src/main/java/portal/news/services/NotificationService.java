package portal.news.services;

import portal.news.dto.notification.NotificationRequestDto;
import portal.news.dto.notification.NotificationResponseDto;
import portal.news.models.Notification;

public interface NotificationService extends CrudService<Notification, NotificationRequestDto, NotificationResponseDto> {
}

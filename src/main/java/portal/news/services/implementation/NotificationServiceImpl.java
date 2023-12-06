package portal.news.services.implementation;
/*
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.models.Notification;
import portal.news.repositories.NotificationRepository;
import portal.news.services.NotificationService;
/*
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    @Override
    public NotificationResponseDto getById(long id) {
        Notification notification = getEntityById(id);

        NotificationResponseDto responseDto = notificationMapper.toDto(notification);
        return responseDto;
    }

    @Override
    @Transactional
    public NotificationResponseDto create(NotificationRequestDto requestDto) {
        Notification notification = notificationMapper.toEntity(requestDto);
        notification = notificationRepository.save(notification);
        NotificationResponseDto responseDto = notificationMapper.toDto(notification);
        return responseDto;
    }

    @Override
    @Transactional
    public NotificationResponseDto update(long id, NotificationRequestDto requestDto) {
        Notification notification = getEntityById(id);
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Notification notification = getEntityById(id);
        notificationRepository.delete(notification);
    }

    @Override
    public Notification getEntityById(long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification with ID " + id + " does not exist"));
    }

}
*/
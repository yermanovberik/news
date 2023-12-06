package portal.news.services;


import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.avatar.AvatarResponseDto;
import portal.news.dto.user.UserRequestDto;
import portal.news.dto.user.UserResponseDto;
import portal.news.models.User;

import java.util.List;

public interface UserService extends CrudService<User, UserRequestDto, UserResponseDto> {

    void throwExceptionIfUserExists(String email);

    void updateSubscriptionStatus(String email);

    AvatarResponseDto createUserWithAvatar(String email, MultipartFile avatarFile);
}


package portal.news.mappers;

import org.mapstruct.Mapper;
import portal.news.dto.user.UserRequestDto;
import portal.news.dto.user.UserResponseDto;
import portal.news.models.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserRequestDto, UserResponseDto> {

    @Override
    UserResponseDto toDto(User entity);

    @Override
    List<UserResponseDto> toDto(List<User> entities);

    @Override
    User toEntity(UserRequestDto request);

    @Override
    List<User> toEntity(List<UserRequestDto> requests);
}

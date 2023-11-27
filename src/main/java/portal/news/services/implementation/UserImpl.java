package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.user.UserRequestDto;
import portal.news.dto.user.UserResponseDto;
import portal.news.exceptions.entity.EntityAlreadyExistsException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.UserMapper;
import portal.news.models.User;
import portal.news.repositories.UserRepository;
import portal.news.services.UserService;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getById(long id) {
        User user = getEntityById(id);

        UserResponseDto responseDto = userMapper.toDto(user);
        return responseDto;
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto requestDto) {
        throwExceptionIfUserExists(requestDto.email());
        User user = userMapper.toEntity(requestDto);
        user = userRepository.save(user);
        UserResponseDto responseDto = userMapper.toDto(user);
        return responseDto;
    }

    @Override
    @Transactional
    public UserResponseDto update(long id, UserRequestDto requestDto) {
        User user = getEntityById(id);
        if (requestDto.email() != null) {
            user.setEmail(requestDto.email());
        }
        if (requestDto.password() != null) {
            user.setPassword(requestDto.password());
        }
        if (requestDto.firstName() != null) {
            user.setFirstName(requestDto.firstName());
        }
        if (requestDto.lastName() != null) {
            user.setLastName(requestDto.lastName());
        }
        if (requestDto.username() != null) {
            user.setUsername(requestDto.username());
        }
        UserResponseDto responseDto = userMapper.toDto(user);
        return responseDto;
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = getEntityById(id);
        userRepository.delete(user);
    }

    @Override
    public User getEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " does not exist"));
    }

    @Override
    public void throwExceptionIfUserExists(String email) {
        userRepository.findByEmail(email)
                .ifPresent(foundUser -> {
                    throw new EntityAlreadyExistsException(
                            "User with the email " + foundUser.getEmail() + " already exists"
                    );
                });
    }



}

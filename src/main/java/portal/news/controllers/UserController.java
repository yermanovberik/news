package portal.news.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.avatar.AvatarResponseDto;
import portal.news.dto.user.UserRequestDto;
import portal.news.dto.user.UserResponseDto;
import portal.news.services.UserService;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserById(
            @PathVariable  long id
    ) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.create(userRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUser(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        userService.delete(id);
    }

    @PutMapping("/update-subscription/{email}")
    public String updateSubscriptionStatus(@PathVariable String email) {
        System.out.println("UserController.updateSubscriptionStatus");
        userService.updateSubscriptionStatus(email);
        return "Subscription status updated successfully!";
    }

    @PostMapping("/createWithAvatar")
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarResponseDto createUserWithAvatar(
            @RequestParam("email") String email,
            @RequestParam("avatarFile") MultipartFile avatarFile) {
        return userService.createUserWithAvatar(email, avatarFile);
    }
}

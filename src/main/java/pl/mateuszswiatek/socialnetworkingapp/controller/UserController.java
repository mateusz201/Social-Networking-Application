package pl.mateuszswiatek.socialnetworkingapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.service.UserService;

@RestController
@Tag(name = "User")
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Valid CreateUserRequestDTO request
    ) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping
    public PageResponseDTO<UserResponseDTO> getUsers(
            @PageableDefault(size = 8, sort = "username", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponseDTO updateUser(
            @PathVariable Long userId,
            @RequestBody @Valid UpdateUserRequestDTO request
    ) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}

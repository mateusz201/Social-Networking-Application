package pl.mateuszswiatek.socialnetworkingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponse;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.service.UserService;

@RestController
@Tag(name = "User")
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Operation(summary = "Endpoint for creating new users")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody @Valid CreateUserRequest request
    ) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Endpoint for getting users")
    @GetMapping
    public PageResponse<UserResponse> getUsers(
            @PageableDefault(size = 8, sort = "username", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return userService.getUsers(pageable);
    }
}

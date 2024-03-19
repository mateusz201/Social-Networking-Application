package pl.mateuszswiatek.socialnetworkingapp.converter;

import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;

import java.util.HashSet;

public class UserConverter {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDescription()
        );
    }

    public static User toEntity(CreateUserRequest request) {
        return new User(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                null,
                new HashSet<>()
        );
    }

    public static User update(User user, UpdateUserRequest request) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return user;
    }
}

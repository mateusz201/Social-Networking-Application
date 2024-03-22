package pl.mateuszswiatek.socialnetworkingapp.converter;

import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;

import java.util.HashSet;

public class UserConverter {
    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDescription()
        );
    }

    public static User toEntity(CreateUserRequestDTO request) {
        return new User(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                null,
                new HashSet<>()
        );
    }

    public static User update(User user, UpdateUserRequestDTO request) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return user;
    }
}

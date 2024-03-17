package pl.mateuszswiatek.socialnetworkingapp.converter;

import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;

public class UserConverter {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDescription()
        );
    }

    public static User toEntity(CreateUserRequest request){
        return new User(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                null
        );
    }
}

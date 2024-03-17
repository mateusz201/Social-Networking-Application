package pl.mateuszswiatek.socialnetworkingapp.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.UserConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.exception.ApiException;
import pl.mateuszswiatek.socialnetworkingapp.repository.UserRepository;

import static pl.mateuszswiatek.socialnetworkingapp.exception.ApiExceptionReason.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request){
        checkIfUsernameIsTaken(request);
        checkIfEmailIsTaken(request);

        User user = UserConverter.toEntity(request);
        User savedUser = userRepository.save(user);

        return UserConverter.toResponse(savedUser);
    }

    private void checkIfEmailIsTaken(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(EMAIL_TAKEN);
        }
    }

    private void checkIfUsernameIsTaken(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(USERNAME_TAKEN);
        }
    }
}

package pl.mateuszswiatek.socialnetworkingapp.service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.PageConverter;
import pl.mateuszswiatek.socialnetworkingapp.converter.UserConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponse;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.exception.ApiException;
import pl.mateuszswiatek.socialnetworkingapp.repository.UserRepository;

import static pl.mateuszswiatek.socialnetworkingapp.exception.ApiExceptionReason.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request) {
        checkIfUsernameIsTaken(request);
        checkIfEmailIsTaken(request);

        User user = UserConverter.toEntity(request);
        User savedUser = userRepository.save(user);

        return UserConverter.toResponse(savedUser);
    }

    public PageResponse<UserResponse> getUsers(Pageable pageable) {
        Page<UserResponse> page = userRepository
                .findAll(pageable)
                .map(UserConverter::toResponse);

        return PageConverter.toResponse(page);
    }

    public UserResponse getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> UserConverter.toResponse(user))
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }

    public UserResponse updateUser(Long userId, UpdateUserRequest request){
        checkIfEmailIsTakenByOtherId(userId, request);
        checkIfUsernameIsTakenByOtherId(userId, request);

        return userRepository.findById(userId)
                .map(user -> UserConverter.update(user,request))
                .map(user -> userRepository.save(user))
                .map(UserConverter::toResponse)
                .orElseThrow(()->new ApiException(USER_NOT_FOUND));
    }

    public void deleteUser(Long userId){
        checkIfUserExists(userId);

        userRepository.deleteById(userId);
    }

    private void checkIfEmailIsTaken(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(EMAIL_TAKEN);
        }
    }

    private void checkIfEmailIsTakenByOtherId(Long userId, UpdateUserRequest request) {
        if(userRepository.existsByEmailAndIdNot(request.getEmail(), userId)){
            throw new ApiException(EMAIL_TAKEN);
        }
    }

    private void checkIfUsernameIsTakenByOtherId(Long userId, UpdateUserRequest request) {
        if(userRepository.existsByUsernameAndIdNot(request.getUsername(), userId)){
            throw new ApiException(USERNAME_TAKEN);
        }
    }

    private void checkIfUsernameIsTaken(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(USERNAME_TAKEN);
        }
    }

    private void checkIfUserExists(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new ApiException(USER_NOT_FOUND);
        }
    }
}

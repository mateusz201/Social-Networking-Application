package pl.mateuszswiatek.socialnetworkingapp.service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.PageConverter;
import pl.mateuszswiatek.socialnetworkingapp.converter.UserConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdateUserRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.exception.ApiException;
import pl.mateuszswiatek.socialnetworkingapp.repository.UserRepository;

import static pl.mateuszswiatek.socialnetworkingapp.exception.ApiExceptionReason.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        checkIfUsernameIsTaken(request);
        checkIfEmailIsTaken(request);

        User user = UserConverter.toEntity(request);
        User savedUser = userRepository.save(user);

        return UserConverter.toResponse(savedUser);
    }

    public PageResponseDTO<UserResponseDTO> getUsers(Pageable pageable) {
        Page<UserResponseDTO> page = userRepository
                .findAll(pageable)
                .map(UserConverter::toResponse);

        return PageConverter.toResponse(page);
    }

    public UserResponseDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(UserConverter::toResponse)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }

    public UserResponseDTO updateUser(Long userId, UpdateUserRequestDTO request) {
        checkIfEmailIsTakenByOtherId(userId, request);
        checkIfUsernameIsTakenByOtherId(userId, request);

        return userRepository.findById(userId)
                .map(user -> UserConverter.update(user, request))
                .map(user -> userRepository.save(user))
                .map(UserConverter::toResponse)
                .orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }

    public void deleteUser(Long userId) {
        checkIfUserExists(userId);

        userRepository.deleteById(userId);
    }

    private void checkIfEmailIsTaken(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(EMAIL_TAKEN);
        }
    }

    private void checkIfEmailIsTakenByOtherId(Long userId, UpdateUserRequestDTO request) {
        if (userRepository.existsByEmailAndIdNot(request.getEmail(), userId)) {
            throw new ApiException(EMAIL_TAKEN);
        }
    }

    private void checkIfUsernameIsTakenByOtherId(Long userId, UpdateUserRequestDTO request) {
        if (userRepository.existsByUsernameAndIdNot(request.getUsername(), userId)) {
            throw new ApiException(USERNAME_TAKEN);
        }
    }

    private void checkIfUsernameIsTaken(CreateUserRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(USERNAME_TAKEN);
        }
    }

    public void checkIfUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ApiException(USER_NOT_FOUND);
        }
    }
}

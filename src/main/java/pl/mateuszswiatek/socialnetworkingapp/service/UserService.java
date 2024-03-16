package pl.mateuszswiatek.socialnetworkingapp.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.UserConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreateUserRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.UserResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request){
        User user = UserConverter.toEntity(request);
        User savedUser = userRepository.save(user);

        return UserConverter.toResponse(savedUser);
    }
}

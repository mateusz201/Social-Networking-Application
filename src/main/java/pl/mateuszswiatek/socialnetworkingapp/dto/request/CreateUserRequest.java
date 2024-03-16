package pl.mateuszswiatek.socialnetworkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @Pattern(regexp = "[a-zA-Z0-9]{4,12}")
    private String username;

    @Pattern(regexp = "[a-zA-Z0-9]{8,20}")
    private String password;

    @Email
    private String email;
}

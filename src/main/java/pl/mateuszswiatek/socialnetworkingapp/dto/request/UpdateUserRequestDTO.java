package pl.mateuszswiatek.socialnetworkingapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRequestDTO {
    @Email
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9]{4,12}")
    private String username;
}

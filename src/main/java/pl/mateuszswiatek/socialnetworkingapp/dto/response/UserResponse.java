package pl.mateuszswiatek.socialnetworkingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String description;
    private String password;
}

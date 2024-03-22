package pl.mateuszswiatek.socialnetworkingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class PostResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserResponseDTO postBy;
}

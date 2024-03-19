package pl.mateuszswiatek.socialnetworkingapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreatePostRequest {
    @NotNull
    private Long userId;
    @Size(max = 200)
    private String content;
}

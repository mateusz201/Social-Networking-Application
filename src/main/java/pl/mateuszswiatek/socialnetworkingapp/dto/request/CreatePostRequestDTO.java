package pl.mateuszswiatek.socialnetworkingapp.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreatePostRequestDTO {
    @NotNull
    private Long userId;
    @NotEmpty
    @Size(max = 200)
    private String content;
}

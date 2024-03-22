package pl.mateuszswiatek.socialnetworkingapp.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdatePostRequestDTO {
    @NotEmpty
    @Size(max = 200)
    private String content;
}



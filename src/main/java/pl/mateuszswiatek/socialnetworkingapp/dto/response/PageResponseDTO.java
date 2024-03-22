package pl.mateuszswiatek.socialnetworkingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PageResponseDTO<T> {
    List<T> content;
    boolean last;
    long totalPages;
}

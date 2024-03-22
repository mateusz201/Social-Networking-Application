package pl.mateuszswiatek.socialnetworkingapp.converter;

import org.springframework.data.domain.Page;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponseDTO;

public class PageConverter {
    public static <T> PageResponseDTO<T> toResponse(Page<T> page) {
        return new PageResponseDTO<>(
                page.getContent(),
                page.isLast(),
                page.getTotalPages()
        );
    }
}

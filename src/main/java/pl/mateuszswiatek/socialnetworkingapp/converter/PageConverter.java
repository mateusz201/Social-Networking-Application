package pl.mateuszswiatek.socialnetworkingapp.converter;

import org.springframework.data.domain.Page;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponse;

public class PageConverter {
    public static <T> PageResponse<T> toResponse(Page<T> page){
        return new PageResponse<>(
                page.getContent(),
                page.isLast(),
                page.getTotalPages()
        );
    }
}

package pl.mateuszswiatek.socialnetworkingapp.converter;

import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdatePostRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;

public class PostConverter {
    public static PostResponseDTO toResponse(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                UserConverter.toResponse(post.getUser())
        );
    }

    public static Post toEntity(CreatePostRequestDTO request, User user) {
        return new Post(
                request.getContent(),
                user
        );
    }

    public static Post update(Post post, UpdatePostRequestDTO request) {
         post.setContent(request.getContent());
         return post;
    }
}

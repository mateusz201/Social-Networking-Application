package pl.mateuszswiatek.socialnetworkingapp.converter;

import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;

public class PostConverter {
    public static PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                UserConverter.toResponse(post.getUser())
        );
    }

    public static Post toEntity(CreatePostRequest request, User user) {
        return new Post(
                request.getContent(),
                user
        );
    }
}

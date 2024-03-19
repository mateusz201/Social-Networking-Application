package pl.mateuszswiatek.socialnetworkingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.PostConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.repository.PostRepository;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostResponse createPost(CreatePostRequest request) {
        User user = userService.getUser(request.getUserId());
        Post post = PostConverter.toEntity(request, user);

        Post savedPost = postRepository.save(post);
        return PostConverter.toResponse(savedPost);
    }
}

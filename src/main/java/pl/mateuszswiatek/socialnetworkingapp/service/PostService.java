package pl.mateuszswiatek.socialnetworkingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mateuszswiatek.socialnetworkingapp.converter.PageConverter;
import pl.mateuszswiatek.socialnetworkingapp.converter.PostConverter;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponse;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponse;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;
import pl.mateuszswiatek.socialnetworkingapp.entity.User;
import pl.mateuszswiatek.socialnetworkingapp.exception.ApiException;
import pl.mateuszswiatek.socialnetworkingapp.repository.PostRepository;

import static pl.mateuszswiatek.socialnetworkingapp.exception.ApiExceptionReason.*;

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

    public PageResponse<PostResponse> getPosts(Pageable pageable) {
        Page<PostResponse> page = postRepository
                .findAll(pageable)
                .map(PostConverter::toResponse);

        return PageConverter.toResponse(page);
    }

    public PostResponse getPostById(Long postId) {
        return postRepository.findById(postId)
                .map(PostConverter::toResponse)
                .orElseThrow(() -> new ApiException(POST_NOT_FOUND));
    }

    public PostResponse updatePost(Long postId, UpdatePostRequest request){
        return postRepository.findById(postId)
                .map(post -> PostConverter.update(post,request))
                .map(post -> postRepository.save(post))
                .map(PostConverter::toResponse)
                .orElseThrow(()->new ApiException(POST_NOT_FOUND));
    }
}

package pl.mateuszswiatek.socialnetworkingapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdatePostRequest;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponse;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponse;
import pl.mateuszswiatek.socialnetworkingapp.service.PostService;

@RestController
@Tag(name = "Post")
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @PostMapping
    public PostResponse createPost(@RequestBody @Valid CreatePostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping
    public PageResponse<PostResponse> getPosts(
            @PageableDefault(size = 8, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/{postId}")
    public PostResponse getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostResponse updatePost(
            @PathVariable Long postId,
            @RequestBody @Valid UpdatePostRequest request
    ) {
        return postService.updatePost(postId, request);
    }
}

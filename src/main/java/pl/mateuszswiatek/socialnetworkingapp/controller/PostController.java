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
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.UpdatePostRequestDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PageResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.dto.response.PostResponseDTO;
import pl.mateuszswiatek.socialnetworkingapp.service.PostService;

@RestController
@Tag(name = "Post")
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @PostMapping
    public PostResponseDTO createPost(@RequestBody @Valid CreatePostRequestDTO request) {
        return postService.createPost(request);
    }

    @GetMapping
    public PageResponseDTO<PostResponseDTO> getPosts(
            @PageableDefault(size = 8, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/{postId}")
    public PostResponseDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostResponseDTO updatePost(
            @PathVariable Long postId,
            @RequestBody @Valid UpdatePostRequestDTO request
    ) {
        return postService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}

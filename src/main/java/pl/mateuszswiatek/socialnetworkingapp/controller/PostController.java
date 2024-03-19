package pl.mateuszswiatek.socialnetworkingapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateuszswiatek.socialnetworkingapp.dto.request.CreatePostRequest;
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

}

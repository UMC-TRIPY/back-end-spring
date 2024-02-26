package com.example.tripy.domain.post;

import com.example.tripy.domain.post.dto.PostRequestDto.CreatePostRequest;
import com.example.tripy.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    /**
     * [POST] 게시글 작성
     */
    @PostMapping("/posts")
    public ApiResponse<String> createPost(
        @RequestBody CreatePostRequest createPostRequest,
        @RequestParam(required = false) Long travelPlanId,
        @RequestParam Long cityId) {
        postService.addPost(createPostRequest, travelPlanId, cityId);
        return ApiResponse.onSuccess("글 작성에 성공했습니다.");
    }
}
package com.example.tripy.domain.post;

import com.example.tripy.domain.post.dto.PostRequestDto.CreatePostRequest;
import com.example.tripy.domain.post.dto.PostResponseDto.GetPostSimpleInfo;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PostMapping("api/posts")
    public ApiResponse<String> createPost(
        @RequestBody CreatePostRequest createPostRequest,
        @RequestParam(required = false) Long travelPlanId,
        @RequestParam Long cityId) {
        postService.addPost(createPostRequest, travelPlanId, cityId);
        return ApiResponse.onSuccess("글 작성에 성공했습니다.");
    }

    /**
     * [DELETE] 게시글 삭제
     */
    @DeleteMapping("api/posts/{postsId}")
    public ApiResponse<String> deletePost(
        @PathVariable   Long postsId) {
        postService.deletePost(postsId);
        return ApiResponse.onSuccess("글 삭제에 성공했습니다.");
    }

    /**
     * [GET] 인기글 TOP 10 조회
     */
    @GetMapping("api/posts/top-ten")
    public ApiResponse<List<GetPostSimpleInfo>> findPostsTopten() {
        return ApiResponse.onSuccess(postService.findPostsTopten());
    }

    /**
     * [GET] 전체 글 추천 많은 순 조회
     */
    @GetMapping("api/posts/top-recommended")
    public ApiResponse<PageResponseDto<List<GetPostSimpleInfo>>> findPostTopRecommended(
        @RequestParam int page, @RequestParam int size
    ) {
        return ApiResponse.onSuccess(postService.findPostsTopRecommended(page, size));
    }
}
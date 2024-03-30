package com.example.tripy.domain.comment;

import com.example.tripy.domain.comment.dto.GetCommentResponse.GetCommentInfo;
import com.example.tripy.domain.post.dto.PostRequestDto.CreatePostRequest;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    /**
     * [POST] 댓글 작성
     */
    @PostMapping("api/comments")
    public ApiResponse<String> createComment(
        @RequestBody CreatePostRequest createPostRequest
    ) {
        commentService.addComment(createPostRequest.getContent());
        return ApiResponse.onSuccess("댓글 작성에 성공했습니다.");
    }

    /**
     * [GET] 댓글 조회
     */
    @GetMapping("/api/{posts-id}/comments")
    public ApiResponse<PageResponseDto<List<GetCommentInfo>>> findComments(
        @RequestParam int page, @RequestParam int size, @PathVariable(value="posts-id") Long postsId

    ) {
        return ApiResponse.onSuccess(commentService.findComments(page, size, postsId));
    }
}
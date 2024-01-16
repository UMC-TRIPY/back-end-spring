package com.example.tripy.domain.post;

import com.example.tripy.domain.post.dto.PostCreateRequestDto;
import com.example.tripy.domain.post.dto.PostResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    /**
     * [POST] 게시글 작성
     */
    @PostMapping
    public ResponseEntity<?> addCorporation(@RequestPart(value = "postCreateRequestDto") @Parameter(schema = @Schema(type = "string", format = MediaType.APPLICATION_JSON_VALUE)) PostCreateRequestDto postCreateRequestDto) throws IOException {
        postService.addPost(postCreateRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * [GET] 전체 글 조회
     */
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPostList(){
        return ResponseEntity.ok(postService.getPostList());
    }

    /**
     * [GET] 도시가 속한 나라별 전체 글 조회
     */
//    @GetMapping("/{cityId}")
//    public ResponseEntity<List<PostResponseDto>> getPostListByCity(@PathVariable Long cityId){
//        return ResponseEntity.ok(postService.getPostList(cityId));
//    }

    /**
     * [GET] 특정 글 상세 조회
     */
    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostDetail(postId));
    }


}

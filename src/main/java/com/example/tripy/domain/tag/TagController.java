package com.example.tripy.domain.tag;

import com.example.tripy.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {

    private final TagService tagService;

    @GetMapping("api/tags")
    public ApiResponse<Long> findTagOrAddTagAndReturnId(
        @RequestParam String tag) {
        return ApiResponse.onSuccess(tagService.findTagOrAddTagAndReturnId(tag));
    }
}

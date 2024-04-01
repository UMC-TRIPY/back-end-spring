package com.example.tripy.domain.comment;

import com.example.tripy.domain.comment.dto.CommentResponseDto.GetCommentInfo;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.post.PostRepository;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    //댓글 추가
    public void addComment(String content, Long postId) {

        Member member = memberRepository.findById(1L)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_MEMBER));

        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_POST));

        Comment comment = Comment.toEntity(member, post, content);
        commentRepository.save(comment);
    }

    //댓글 조회
    PageResponseDto<List<GetCommentInfo>> findComments(int page, int size, Long postsId) {

        Pageable pageable = PageRequest.of(page, size);
        Post post = postRepository.findById(postsId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_POST));

        Page<Comment> commentList = commentRepository.findByPost(post, pageable);

        List<GetCommentInfo> dtoList = commentList.stream()
            .map(comment -> GetCommentInfo.toDto(comment, comment.getMember()))
            .toList();

        return new PageResponseDto<>(commentList.getNumber(), commentList.getTotalPages(), dtoList);
    }
}

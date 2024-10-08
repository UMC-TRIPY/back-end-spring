package com.example.tripy.domain.posttag;

import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.tag.Tag;
import com.example.tripy.domain.tag.TagRepository;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostTagService {

    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void savePostTag(Post post, List<Long> tags) {
        tags.forEach(tagId -> {
            Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_TAG));

            PostTag postTag = PostTag.toEntity(post, tag);
            postTagRepository.save(postTag);
        });
    }

    @Transactional
    public void deletePostTagsByPost(Post post) {
        postTagRepository.deleteByPost(post);
    }

    //게시글 태그 가져오기
    public List<String> findTagsStringByPost(Post post) {
        List<PostTag> postTagList = postTagRepository.findAllByPost(post);

        return postTagList.stream()
            .map(postTag -> postTag.getTag().getTagName())
            .toList();
    }
}
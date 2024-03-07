package com.example.tripy.domain.post;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.city.CityRepository;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.domain.post.dto.PostRequestDto.CreatePostRequest;
import com.example.tripy.domain.post.dto.PostResponseDto.GetPostSimpleInfo;
import com.example.tripy.domain.postfile.FileType;
import com.example.tripy.domain.postfile.PostFileService;
import com.example.tripy.domain.posttag.PostTagService;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.travelplan.TravelPlanRepository;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final CityRepository cityRepository;
    private final PostFileService postFileService;
    private final MemberRepository memberRepository;
    private final PostTagService postTagService;

    @Transactional
    public void addPost(CreatePostRequest createPostRequest, Long travelPlanId, Long cityId) {

        Member member = memberRepository.findById(1L)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_MEMBER));

        TravelPlan travelPlan = null;
        if (travelPlanId != null) {
            travelPlan = travelPlanRepository.findById(travelPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_TRAVEL_PLAN));
        }
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_CITY));

        Post post = Post.toEntity(createPostRequest, member, city, travelPlan);

        postRepository.save(post);
        if(createPostRequest.getImageUrls() != null) {
            addImages(post, createPostRequest.getImageUrls());
        }
        if(createPostRequest.getFileUrls() != null) {
            addFiles(post, createPostRequest.getFileUrls());
        }
        if(createPostRequest.getTagIds() != null) {
            addTags(post, createPostRequest.getTagIds());
        }
    }

    private void addImages(Post post, List<String> imageUrls) {
        postFileService.saveFilesByType(post, imageUrls, FileType.IMAGE);
    }

    private void addFiles(Post post, List<String> fileUrls) {
        postFileService.saveFilesByType(post, fileUrls, FileType.FILE);
    }

    private void addTags(Post post, List<Long> tagIds) {
        postTagService.savePostTag(post, tagIds);
    }

    public void deletePost(Long postsId) {
        Member member = memberRepository.findById(1L)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_MEMBER));

        Post post = postRepository.findById(postsId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_POST));

        postFileService.deleteFilesByPost(post);
        postTagService.deletePostTagsByPost(post);

        postRepository.delete(post);
    }

    //TOP 10 조회
    public List<GetPostSimpleInfo> findPostsTopten() {

        Pageable pageable = PageRequest.of(0, 10);
        List<Post> postList = postRepository.findByRankToptenOrderByRank(pageable);

        return postList.stream()
            .map(GetPostSimpleInfo::toDto)
            .toList();
    }

    //추천순 조회
    public PageResponseDto<List<GetPostSimpleInfo>> findPostsTopRecommended(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postList = postRepository.findByTopRecommended(pageable);

        List<GetPostSimpleInfo> postDtoList = postList.stream()
            .map(GetPostSimpleInfo::toDto)
            .toList();

        return new PageResponseDto<>(postList.getNumber(), postList.getTotalPages(),
        postDtoList);
    }


}
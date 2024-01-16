package com.example.tripy.domain.post;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.city.CityRepository;
import com.example.tripy.domain.post.dto.PostCreateRequestDto;
import com.example.tripy.domain.post.dto.PostResponseDto;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.travelplan.TravelPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final CityRepository cityRepository;

    // TODO: 2024/01/01 예외처리 및 MultipartFile 처리
    @Transactional
    public void addPost(PostCreateRequestDto postCreateRequestDto){
        try{
            TravelPlan travelPlan = travelPlanRepository.findById(postCreateRequestDto.getTravelPlanId()).get();
            Post post = Post.toEntity(postCreateRequestDto, travelPlan);
            postRepository.save(post);
        }
       catch (Exception e){
       }
    }

    /**
     * 전체 글 조회
     */

    public List<PostResponseDto> getPostList(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponseDto::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 도시가 포함된 나라별 전체 글 조회
     */
    public List<PostResponseDto> getPostList(Long cityId){
        City city = cityRepository.findById(cityId).get();
        Long countryId = city.getCountry().getId();
        List<Post> posts = postRepository.findByCountry(countryId);

        return posts.stream()
                .map(PostResponseDto::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 글 하나 조회
     */
    public PostResponseDto getPostDetail(Long postId){
        Post post = postRepository.findById(postId).get();
        post.viewCountUp();
        return PostResponseDto.toDTO(post);
    }

}

package com.example.tripy.domain.posttag;

import com.example.tripy.domain.post.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    void deleteByPost(Post post);


    List<PostTag> findAllByPost(Post post);
}

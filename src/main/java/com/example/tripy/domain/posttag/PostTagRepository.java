package com.example.tripy.domain.posttag;

import com.example.tripy.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    void deleteByPost(Post post);
}

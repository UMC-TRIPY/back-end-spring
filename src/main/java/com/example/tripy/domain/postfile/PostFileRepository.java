package com.example.tripy.domain.postfile;

import com.example.tripy.domain.post.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<PostFile, Long> {

    List<PostFile> findAllByPost(Post post);
}
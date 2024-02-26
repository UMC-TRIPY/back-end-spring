package com.example.tripy.domain.postfile;

import com.example.tripy.domain.post.Post;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostFileService {

    private final PostFileRepository postFileRepository;

    @Transactional
    public void saveFilesByType(Post post, List<String> urls, FileType fileType) {
        urls.forEach(url -> {
            PostFile postFile = PostFile.toEntity(post, url, fileType);
            postFileRepository.save(postFile);
        });
    }
}
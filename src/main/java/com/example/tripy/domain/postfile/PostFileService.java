package com.example.tripy.domain.postfile;

import com.example.tripy.domain.post.Post;
import com.example.tripy.global.s3.S3Service;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostFileService {

    private final PostFileRepository postFileRepository;
    private final S3Service s3Service;

    @Transactional
    public void saveFilesByType(Post post, List<String> urls, FileType fileType) {
        urls.forEach(url -> {
            PostFile postFile = PostFile.toEntity(post, url, fileType);
            postFileRepository.save(postFile);
        });
    }

    @Transactional
    public void deleteFilesByPost(Post post) {
        List<PostFile> postFiles = postFileRepository.findAllByPost(post);

        for(PostFile postFile : postFiles) {
            s3Service.deleteFile(s3Service.parseFileName(postFile.getUrl()));
        }
    }
}
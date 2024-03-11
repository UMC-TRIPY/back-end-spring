package com.example.tripy.domain.tag;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public Long findTagOrAddTagAndReturnId(String tagName) {

        Optional<Tag> tagOptional = tagRepository.findByTagName(tagName);
        Tag tag;
        if(tagOptional.isPresent()) {
            return tagOptional.get().getId();
        }

        tag = Tag.toEntity(tagName);
        tagRepository.save(tag);
        return tag.getId();
    }
}

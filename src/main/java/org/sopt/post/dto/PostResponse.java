package org.sopt.post.dto;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        Category category,
        String authorName,
        LocalDateTime createdAt,
        long viewCount
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getMember().getName(),
                post.getCreatedAt(),
                post.getViewCount()
        );
    }
}

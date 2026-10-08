package org.sopt.post.domain;

import java.time.LocalDateTime;

public class Post {
    final private Long id;
    private String title;
    private String content;
    final private Category category;
    final private String author;
    final private LocalDateTime createdAt ;

    public Post(Long id, String title, String content, Category category, String author) {

        validateNotBlank(title, "제목");
        validateNotBlank(content, "내용");

        this.title = title;
        this.content = content;
        this.category = category;
        this.id = id;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }
    // 역할 부여
    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void updateTitle(String title) {
        validateNotBlank(title, "제목");
        this.title = title;
    }

    public void updateContent(String content) {

        validateNotBlank(content, "내용");
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void validateNotBlank(String value, String fieldName) {
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + "은(는) 비어있을 수 없습니다.");
        }
    }
}

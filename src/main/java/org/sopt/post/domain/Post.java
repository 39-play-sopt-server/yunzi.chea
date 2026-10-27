package org.sopt.post.domain;

import jakarta.persistence.*;
import org.sopt.post.exception.InvalidPostException;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String author;
    private LocalDateTime createdAt;
    private long viewCount;

    protected Post() {}

    public Post(String title, String content, Category category, String author) {

        validateNotBlank(title, "제목");
        validateNotBlank(content, "내용");

        this.title = title;
        this.content = content;
        this.category = category;
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

    public void update(String title, String content) {
        validateNotBlank(title, "제목");
        validateNotBlank(content,"내용");

        this.title = title;
        this.content = content;
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
            throw new InvalidPostException(fieldName + "은(는) 비어있을 수 없습니다.");
        }
    }

    public long getViewCount() {
        return viewCount;
    }

    public void increaseViewCount() {
        ++viewCount;
    }
}

package org.sopt.post;

import org.sopt.domain.Category;

import java.time.LocalDateTime;

public class Post {
    final private Long id;
    private String title;
    private String content;
    final private Category category;
    final private String author;
    final private LocalDateTime createdAt ;

    public Post(Long id, String title, String content, Category category, String author) {
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
        this.title = title;
    }

    public void updateContent(String content) {
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
}

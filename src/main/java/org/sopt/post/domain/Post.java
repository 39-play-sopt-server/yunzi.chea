package org.sopt.post.domain;

import jakarta.persistence.*;
import org.sopt.common.BaseEntity;
import org.sopt.member.domain.Member;
import org.sopt.post.exception.InvalidPostException;

@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    private long viewCount;

    protected Post() {}

    public Post(String title, String content, Category category,Member member) {

        validateNotBlank(title, "제목");
        validateNotBlank(content, "내용");

        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
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

    public Member getMember() {
        return member;
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

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
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    private long viewCount;

    protected Post() {}

    public Post(String title, String content, Category category,Member member) {

        validateNotBlank(title, "제목");
        validateNotBlank(content, "내용");
        validateNotNull(category, "카테고리");
        validateNotNull(member, "회원");

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

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new InvalidPostException(fieldName + "은(는) 필수입니다.");
        }
    }

    private void validateNotBlank(String value, String fieldName) {
        if(value == null || value.isBlank()){
            throw new InvalidPostException(fieldName + "은(는) 비어있을 수 없습니다.");
        }
    }

    public long getViewCount() {
        return viewCount;
    }

}

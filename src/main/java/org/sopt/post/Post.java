package org.sopt.post;

public class Post {
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
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

}

package org.sopt.post.domain;

public enum Category {

    NOTICE("공지"), FREE("자유"), QUESTION("질문"), REVIEW("리뷰");
    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}

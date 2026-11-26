package org.sopt.post.exception;

public class PostForbiddenException extends RuntimeException {

    public PostForbiddenException() {
        super("본인이 작성한 게시글만 수정/삭제할 수 있습니다.");
    }
}

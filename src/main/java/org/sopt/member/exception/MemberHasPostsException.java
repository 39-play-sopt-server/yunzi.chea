package org.sopt.member.exception;

public class MemberHasPostsException extends RuntimeException {
    public MemberHasPostsException() {
        super("회원이 작성한 게시글이 존재합니다.");
    }
}

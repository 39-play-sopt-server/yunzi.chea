package org.sopt.common;

import org.sopt.member.exception.InvalidMemberException;
import org.sopt.member.exception.MemberHasPostsException;
import org.sopt.member.exception.MemberNotFoundException;
import org.sopt.post.dto.ApiResponse;
import org.sopt.post.exception.InvalidPostException;
import org.sopt.post.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND,e.getMessage()));
    }

    @ExceptionHandler(InvalidPostException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidPostException(InvalidPostException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberNotFoundException(MemberNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(InvalidMemberException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidMemberException(InvalidMemberException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MemberHasPostsException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberHasPostsException(MemberHasPostsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(HttpStatus.CONFLICT, e.getMessage()));
    }
}

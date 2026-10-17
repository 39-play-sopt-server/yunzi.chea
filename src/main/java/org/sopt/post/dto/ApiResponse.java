package org.sopt.post.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        Result result,
        T data
) {
    public record Result(int code, boolean success, String message) { }

    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(new Result(status.value(), true, message), data);
    }

    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return new ApiResponse<>(new Result(status.value(), false, message), null);
    }
}

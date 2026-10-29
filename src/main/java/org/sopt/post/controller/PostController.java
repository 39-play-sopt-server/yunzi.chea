package org.sopt.post.controller;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.*;
import org.sopt.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestBody CreatePostRequest request
    ) {
        Post post = postService.createPost(
                request.title(), request.content(), request.category(), request.memberId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED, "게시글이 작성되었습니다.", PostResponse.from(post)));
    }

    @GetMapping
    public ApiResponse<PageResponse<PostResponse>> getPostList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<PostResponse> posts = postService.getAllPosts(pageable)
                .map(PostResponse::from);
        return ApiResponse.success(HttpStatus.OK,"게시글 목록 조회 성공", PageResponse.from(posts));
    }

    @GetMapping(path = "/{postId}")
    public ApiResponse<PostResponse> getPost(
            @PathVariable(name = "postId") Long postId
    ) {
        Post post = postService.readPost(postId);
        return ApiResponse.success(HttpStatus.OK,"게시글 조회 성공", PostResponse.from(post));
    }

    @PutMapping(path = "/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody UpdatePostRequest request
    ) {
        postService.updatePost(postId, request.title(), request.content());
        Post post = postService.getPost(postId);
        return ApiResponse.success(HttpStatus.OK,"게시글이 수정되었습니다.", PostResponse.from(post));
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable(name = "postId") Long postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK,"삭제 완료",null));
    }
}

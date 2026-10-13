package org.sopt.post.controller;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;
import org.sopt.post.service.PostService;
import org.sopt.post.view.PostView;

import java.util.List;

public class PostController {
    private final PostView postView;
    private final PostService postService;

    public PostController(PostView view,PostService postService) {
        this.postView = view;
        this.postService = postService;
    }

    public void createPost() {
        String title = postView.inputTitle();
        String content = postView.inputContent();
        String author = postView.inputAuthor();
        Category category = postView.inputCategory();

        postService.createPost(title, content,category,author);
        postView.printMessage("게시글이 작성되었습니다.");
    }

    public void checkPostList() {
        List<Post> postList = postService.getAllPosts();
        if(postList.isEmpty()) {
            postView.printMessage("게시글이 없습니다.");
        } else {
            postView.printPostList(postList);
        }
    }

    public void checkPost() {

        Long id = postView.inputIndex("조회할 게시글 번호: ");
        Post post = postService.getPost(id);
        postView.printPost(post);
    }

    public void updatePost() {
        Long id = postView.inputIndex("수정할 게시글 번호: ");
        String newTitle = postView.inputNewTitle();
        String newContent = postView.inputNewContent();
        postService.updatePost(id, newTitle, newContent);
        postView.printMessage("게시글이 수정되었습니다.");
    }

    public void deletePost() {
        Long id = postView.inputIndex("삭제할 게시글 번호: ");
        postService.deletePost(id);
        postView.printMessage("게시글이 삭제되었습니다.");
    }
}

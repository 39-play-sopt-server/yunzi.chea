package org.sopt.post.service;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, Category category, String author) {
        return postRepository.save(title, content, category,author);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost (Long id) {
        return postRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public void updatePost (Long id, String title, String content) {
        Post post = getPost(id);
        post.updateTitle(title);
        post.updateContent(content);
    }

    public void deletePost(Long id) {
        getPost(id);
        postRepository.deleteById(id);
    }
}

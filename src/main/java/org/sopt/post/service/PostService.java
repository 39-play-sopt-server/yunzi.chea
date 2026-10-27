package org.sopt.post.service;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, Category category, String author) {
        return postRepository.save(new Post(title, content, category, author));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost (Long id) {
        return postRepository.findById(id).orElseThrow(() ->new PostNotFoundException());
    }

    @Transactional
    public void updatePost (Long id, String title, String content) {
        Post post = getPost(id);
        post.update(title, content);
    }

    @Transactional
    public void deletePost(Long id) {
        getPost(id);
        postRepository.deleteById(id);
    }
    public Post readPost(Long id) {
        Post post = getPost(id);
        post.increaseViewCount();
        return post;
    }
    public Post save(String title, String content, Category category, String author) {
        Post nextPost = new Post(title, content, category, author);
        postRepository.save(nextPost);
        return nextPost;
    }
}

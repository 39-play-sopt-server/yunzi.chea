package org.sopt.post.repository;

import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostRepository {

    // 게시글을 담기.
    private final Map<Long,Post> posts = new LinkedHashMap<>();
    private long nextId = 0;

    public Post save(String title, String content, Category category, String author) {
        nextId++;
        Post nextPost = new Post(nextId,title, content, category, author);
        posts.put(nextId,nextPost);
        return nextPost;
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public List<Post> findAll () {
        return new ArrayList<>(posts.values());
    }

    public void deleteById(Long id) {
        posts.remove(id);
    }
}

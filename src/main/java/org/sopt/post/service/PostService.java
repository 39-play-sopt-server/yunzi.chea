package org.sopt.post.service;

import org.sopt.member.domain.Member;
import org.sopt.member.service.MemberService;
import org.sopt.post.domain.Category;
import org.sopt.post.domain.Post;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;

    public PostService(PostRepository postRepository, MemberService memberService){
        this.postRepository = postRepository;
        this.memberService = memberService;
    }


    public Post createPost(String title, String content, Category category, Long memberId) {
        return postRepository.save(new Post(title, content, category, memberService.getMember(memberId)));
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
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
    public Post save(String title, String content, Category category, Member member) {
        Post nextPost = new Post(title, content, category, member);
        postRepository.save(nextPost);
        return nextPost;
    }
}

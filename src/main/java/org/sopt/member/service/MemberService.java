package org.sopt.member.service;

import org.sopt.member.domain.Member;
import org.sopt.member.exception.MemberHasPostsException;
import org.sopt.member.exception.MemberNotFoundException;
import org.sopt.member.repository.MemberRepository;
import org.sopt.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    public MemberService(MemberRepository memberRepository,PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Member createMember(String name) {
        return memberRepository.save(new Member(name));
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException());
    }

    @Transactional
    public void deleteMember(Long id) {
        getMember(id);
        if(postRepository.existsByMemberId(id)) { throw new MemberHasPostsException(); }
        memberRepository.deleteById(id);
    }

}

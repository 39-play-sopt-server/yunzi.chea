package org.sopt.member.controller;

import org.sopt.member.domain.Member;
import org.sopt.member.dto.CreateMemberRequest;
import org.sopt.member.dto.MemberResponse;
import org.sopt.member.service.MemberService;
import org.sopt.post.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse>> createMember(
            @RequestBody CreateMemberRequest request
            ) {
        Member member = memberService.createMember(request.name());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED, "회원이 생성되었습니다.", MemberResponse.from(member)));
    }

    @DeleteMapping(path = "/{memberId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(
            @PathVariable(name = "memberId") Long memberId
    ) {
        memberService.deleteMember(memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK,"회원이 삭제되었습니다.",null));
    }
}

package org.sopt.member.controller;

import org.sopt.common.jwt.RefreshTokenCookieProvider;
import org.sopt.member.service.MemberService;
import org.sopt.post.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final RefreshTokenCookieProvider refreshTokenCookieProvider;

    public MemberController(MemberService memberService, RefreshTokenCookieProvider refreshTokenCookieProvider) {
        this.memberService = memberService;
        this.refreshTokenCookieProvider = refreshTokenCookieProvider;
    }

    @DeleteMapping(path = "/me")
    public ResponseEntity<ApiResponse<Void>> deleteMember(
            @AuthenticationPrincipal Long memberId
    ) {
        memberService.deleteMember(memberId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookieProvider.expire().toString())
                .body(ApiResponse.success(HttpStatus.OK,"회원이 삭제되었습니다.",null));
    }

    @GetMapping("/me")
    public ResponseEntity<Long> me(@AuthenticationPrincipal Long memberId) {
        return ResponseEntity.ok(memberId);
    }
}

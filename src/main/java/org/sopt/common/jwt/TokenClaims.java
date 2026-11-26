package org.sopt.common.jwt;

// 검증이 끝난 토큰에서 꺼낸 값
public record TokenClaims(Long memberId, TokenType type) {
}

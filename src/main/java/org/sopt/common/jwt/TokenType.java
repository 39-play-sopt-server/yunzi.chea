package org.sopt.common.jwt;

import io.jsonwebtoken.JwtException;

import java.util.Arrays;

public enum TokenType {
    ACCESS("access"),
    REFRESH("refresh");

    private final String claimValue;

    TokenType(String claimValue) {
        this.claimValue = claimValue;
    }

    public String getClaimValue() {
        return claimValue;
    }

    // 토큰의 type claim 문자열을 enum으로 변환. 알 수 없는 값이면 유효하지 않은 토큰으로 간주
    public static TokenType from(String claimValue) {
        return Arrays.stream(values())
                .filter(type -> type.claimValue.equals(claimValue))
                .findFirst()
                .orElseThrow(() -> new JwtException("알 수 없는 토큰 타입입니다."));
    }
}

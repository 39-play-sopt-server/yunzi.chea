package org.sopt.common.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

// 토큰 발급만 담당한다.
@Component
public class JwtTokenGenerator {

    private static final String TYPE_CLAIM = "type";

    private final JwtProperties jwtProperties;

    public JwtTokenGenerator(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createAccessToken(Long memberId) {
        return createToken(memberId, TokenType.ACCESS);
    }

    public String createRefreshToken(Long memberId) {
        return createToken(memberId, TokenType.REFRESH);
    }

    private String createToken(Long memberId, TokenType type) {
        Date now = new Date();

        return Jwts.builder()
                .subject(String.valueOf(memberId))                                   // 누구의 토큰인지
                .claim(TYPE_CLAIM, type.getClaimValue())                             // 토큰 타입
                .issuedAt(now)                                                       // 발급 시각
                .expiration(new Date(now.getTime() + jwtProperties.getExpiration(type))) // 만료 시각
                .signWith(jwtProperties.getSecretKey())                              // 서명
                .compact();
    }
}

package org.sopt.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

// 토큰 검증과 파싱만 담당한다. 서명/만료가 잘못되면 JwtException이 발생한다.
@Component
public class JwtTokenParser {

    private static final String TYPE_CLAIM = "type";

    private final JwtProperties jwtProperties;

    public JwtTokenParser(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public TokenClaims parse(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(jwtProperties.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long memberId = Long.valueOf(claims.getSubject());
        TokenType type = TokenType.from(claims.get(TYPE_CLAIM, String.class));

        return new TokenClaims(memberId, type);
    }
}

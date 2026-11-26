package org.sopt.common.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RefreshTokenCookieProvider {

    public static final String COOKIE_NAME = "refreshToken";
    private static final String COOKIE_PATH = "/auth/reissue";

    private final Duration maxAge;

    public RefreshTokenCookieProvider(
            @Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration
    ) {
        this.maxAge = Duration.ofMillis(refreshTokenExpiration);
    }

    public ResponseCookie create(String refreshToken) {
        return build(refreshToken, maxAge);
    }

    public ResponseCookie expire() {
        return build("", Duration.ZERO);
    }

    private ResponseCookie build(String value, Duration maxAge) {
        return ResponseCookie.from(COOKIE_NAME, value)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path(COOKIE_PATH)
                .maxAge(maxAge)
                .build();
    }
}

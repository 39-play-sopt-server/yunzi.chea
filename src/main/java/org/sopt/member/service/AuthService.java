package org.sopt.member.service;

import org.sopt.common.jwt.JwtTokenGenerator;
import org.sopt.common.jwt.JwtTokenParser;
import org.sopt.common.jwt.TokenClaims;
import org.sopt.common.jwt.TokenType;
import org.sopt.member.domain.Member;
import org.sopt.member.dto.LoginResult;
import org.sopt.member.exception.AuthenticationFailedException;
import org.sopt.member.exception.DuplicateEmailException;
import org.sopt.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenParser jwtTokenParser;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
                       JwtTokenGenerator jwtTokenGenerator, JwtTokenParser jwtTokenParser) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenParser = jwtTokenParser;
    }

    public String reissue(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new AuthenticationFailedException("리프레시 토큰이 필요합니다.");
        }

        TokenClaims claims = jwtTokenParser.parse(refreshToken);

        if (claims.type() != TokenType.REFRESH) {
            throw new AuthenticationFailedException("유효하지 않은 토큰입니다.");
        }

        return jwtTokenGenerator.createAccessToken(claims.memberId());
    }

    @Transactional
    public Long signup(String email, String name, String password) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }

        Member member = new Member(name, email, passwordEncoder.encode(password));
        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public LoginResult login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationFailedException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new AuthenticationFailedException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return new LoginResult(
                jwtTokenGenerator.createAccessToken(member.getId()),
                jwtTokenGenerator.createRefreshToken(member.getId()));
    }
}

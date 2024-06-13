package com.example.cookers.domain.member.service;

import com.example.cookers.domain.Email.Email;
import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.entity.Role;
import com.example.cookers.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public Member signup(String providerTypeCode, String username, String password, String passwordConfirm, String nickname, String email, Long hit, String url) {

        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Member member = Member
                .builder()
                .providerTypeCode(providerTypeCode)
                .username(username)
                .password(passwordEncoder.encode(password))
                .profile_url(url)
                .nickname(nickname)
                .email(email)
                .hit(hit)
                .role(Role.USER)  // 기본적으로 USER 권한을 부여
                .build();


        emailService.send(email, "오내요 회원가입을 환영합니다!", "오내요 회원가입이 정상적으로 완료되었습니다^^~!");
        return memberRepository.save(member);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl, String email) {
        Optional<Member> opMember = findByUsernameAndProviderTypeCode(username, providerTypeCode);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(providerTypeCode, username,  "", "",nickname, email, 0L, profileImageUrl); // 최초 로그인 시 딱 한번 실행
    }

    @Transactional
    public Member signupGoogle(String username, String nickname, String email) {
        // 구글 로그인으로부터 받은 정보로 Member 객체 생성
        Member member = Member.builder()
                .username(username)
                .password("") // 구글 로그인으로부터 받은 정보만으로 회원가입이 이루어지므로 비밀번호는 필요하지 않음
                .nickname(nickname)
                .email(email)
                .build();

        // 회원 저장
        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByusername(username);
    }
    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
    }
}
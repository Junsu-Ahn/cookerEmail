package com.example.cookers.domain.member.service;

import com.example.cookers.domain.Email.Email;
import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.entity.Role;
import com.example.cookers.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;
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

        if (memberRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }

        // 중복 닉네임 확인
        if (memberRepository.existsByNickname(nickname)) {
            throw new DataIntegrityViolationException("이미 존재하는 닉네임입니다.");
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


        // emailService.send(email, "오내요 회원가입을 환영합니다!", "오내요 회원가입이 정상적으로 완료되었습니다^^~!");
        return memberRepository.save(member);
    }

    @Transactional
    public Member createAdmin(String username, String password) {
        if (memberRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }

        Member admin = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.ADMIN)
                .build();

        return memberRepository.save(admin);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl, String email) {
        Optional<Member> opMember = findByUsernameAndProviderTypeCode(username, providerTypeCode);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(providerTypeCode, username,  "", "",nickname, email, 0L, profileImageUrl); // 최초 로그인 시 딱 한번 실행
    }


    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByusername(username);
    }
    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
    }
    public List<Member> findByUserEmail(String email) {
        return memberRepository.findByemail(email);
    }
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public boolean authenticateMember(String username, String password) {
        Optional<Member> memberOptional = memberRepository.findByusername(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return passwordEncoder.matches(password, member.getPassword());
        }
        return false;
    }
}
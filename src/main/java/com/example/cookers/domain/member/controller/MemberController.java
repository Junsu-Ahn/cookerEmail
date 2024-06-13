package com.example.cookers.domain.member.controller;


import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @ControllerAdvice
    @RequiredArgsConstructor
    public class GlobalControllerAdvice {
        private final MemberRepository memberRepository;

        @ModelAttribute
        public void addAttributes(Model model, Principal principal) {
            if (principal != null) {
                String username = principal.getName();
                Optional<Member> memberOptional = memberRepository.findByusername(username);
                if (memberOptional.isPresent()) {
                    Member member = memberOptional.get();
                    String profileImageUrl = member.getProfile_url();
                    model.addAttribute("profileImageUrl", profileImageUrl);
                }
            }
        }
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {

        return "member/login";
    }

    @PostMapping("/login")
    public String login() {
        return "/member/login";
    }


    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignForm signForm, Model model) {
        memberService.signup(signForm.getProviderTypeCode(), signForm.getUsername(), signForm.getPassword(), signForm.getPassword_confirm(), signForm.getNickname(), signForm.getEmail(), 0L, signForm.getProfile_url());
        return "redirect:/member/login";
    }

    @PostMapping("/signup/google")
    public String signupGoogle(@Valid GoogleSignForm signForm) {
        memberService.signupGoogle(signForm.getUsername(), signForm.getNickname(), signForm.getEmail());
        return "redirect:/member/login"; // 회원가입 후 메인 페이지로 이동하도록 수정
    }

    @ToString
    @Getter
    @Setter
    public static class SignForm {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String password_confirm;

        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        private Long hit;

        private String profile_url;

        private String providerTypeCode;

        private String role; // 권한 필드 추가
    }

    @ToString
    @Getter
    @Setter
    public static class GoogleSignForm {
        @NotBlank
        private String username;

        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        private String profileUrl;
    }

}
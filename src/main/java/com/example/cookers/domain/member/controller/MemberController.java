package com.example.cookers.domain.member.controller;


import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;
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
    public String signup(@Valid SignForm signForm) {
        memberService.signup(signForm.getProviderTypeCode(), signForm.getUsername(), signForm.getPassword(), signForm.getPassword_confirm(), signForm.getNickname(), signForm.getEmail(), 0L, signForm.getProfile_url());
        return "redirect:/member/login";
    }

    @GetMapping("/findId")
    public String find_id() {
        return "member/findId";
    }

    @PostMapping("/findId")
    public String find_id2(@RequestParam("email") String email, Model model) {
        List<Member> members = memberService.findByUserEmail(email);
        if(members.isEmpty())  // 멤버를 찾을 수 없는 경우 처리
        { model.addAttribute("error", "입력하신 이메일로 등록된 계정이 없습니다.");
            return "member/findId";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h2>당신의 오내요 아이디는 다음과 같습니다:</h2>");
        sb.append("<ul>");

        // 멤버들의 아이디를 StringBuilder에 추가
        for (Member member : members) {
            sb.append("<li>").append(member.getUsername()).append("</li>");
        }

        sb.append("</ul>");
        sb.append("</body></html>");

        // 이메일 발송
        emailService.sendHtml(email, "당신의 오내요 아이디 입니다!", sb.toString());

        return "redirect:/member/login";
    }


    @GetMapping("find_pw")
    public String find_pw() {
        return "member/find_pw";
    }

    @PostMapping("/find_pw")
    public String find_pw2() {
        return "redirect:/member/find_pw";
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
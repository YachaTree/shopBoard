package com.tree.shop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    /**
     * 회원추가 페이지
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    /**
     * 회원추가
     * @param name
     * @param password
     */
    @PostMapping("/member")
    public String addMember(String name, String username, String password) {
        memberService.saveMember(name, username, password);
        return "redirect:/list";
    }

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String loginP() { return "login.html";}

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {

        return "myPage.html";
    }
//    @PostMapping("/login")
//    public String login(String username, String password) {
//
//    }

}

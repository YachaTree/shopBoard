package com.tree.shop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveMember(String name, String username, String password) {
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setUsername(username);
        newMember.setPassword(passwordEncoder.encode(password));
        memberRepository.save(newMember);
    }
}

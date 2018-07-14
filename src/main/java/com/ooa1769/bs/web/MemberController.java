package com.ooa1769.bs.web;

import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "registrationPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(MemberDto memberDto) {
        memberService.register(memberDto);
        return "redirect:/";
    }
}

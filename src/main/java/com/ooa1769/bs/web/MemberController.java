package com.ooa1769.bs.web;

import com.ooa1769.bs.dto.MemberDto;
import com.ooa1769.bs.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MemberDto memberDto) {
        memberService.register(memberDto.toMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

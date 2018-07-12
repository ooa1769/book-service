package com.ooa1769.bs.web.dto;

import com.ooa1769.bs.domain.member.Member;
import lombok.Data;

@Data
public class MemberDto {

    private String email;

    private String password;

    private String name;

    public Member toMember() {
        return new Member(email, name, password, true);
    }
}

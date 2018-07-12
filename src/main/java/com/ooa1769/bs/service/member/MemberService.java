package com.ooa1769.bs.service.member;

import com.ooa1769.bs.domain.member.Member;
import com.ooa1769.bs.web.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Member register(MemberDto memberDto);

    void addSearchHistory(Member member, String searchKeyword);
}

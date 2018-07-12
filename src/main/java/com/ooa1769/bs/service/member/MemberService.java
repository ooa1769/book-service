package com.ooa1769.bs.service.member;

import com.ooa1769.bs.domain.member.Member;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Member register(Member member);

    void addSearchHistory(Member member, String searchKeyword);
}

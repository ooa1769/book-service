package com.ooa1769.bs.service.member;

import com.ooa1769.bs.domain.book.SearchHistory;
import com.ooa1769.bs.domain.member.Member;
import com.ooa1769.bs.respository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member register(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void addSearchHistory(Member member, String searchKeyword) {
        member.addSearchHistory(new SearchHistory(searchKeyword));
        memberRepository.save(member);
    }
}

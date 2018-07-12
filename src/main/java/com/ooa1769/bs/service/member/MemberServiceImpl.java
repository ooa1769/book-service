package com.ooa1769.bs.service.member;

import com.ooa1769.bs.domain.book.SearchHistory;
import com.ooa1769.bs.domain.member.Member;
import com.ooa1769.bs.web.dto.MemberDto;
import com.ooa1769.bs.respository.member.MemberRepository;
import com.ooa1769.bs.web.error.MemberAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member register(MemberDto memberDto) {
        if (emailExist(memberDto.getEmail())) {
            throw new MemberAlreadyExistException("이미 등록된 id입니다. " + memberDto.getEmail());
        }
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return memberRepository.save(memberDto.toMember());
    }

    @Override
    public void addSearchHistory(Member member, String searchKeyword) {
        member.addSearchHistory(new SearchHistory(searchKeyword));
        memberRepository.save(member);
    }

    private boolean emailExist(final String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}

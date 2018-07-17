package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.SearchHistory;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SearchHistoryService {

    private final MemberRepository memberRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryService(MemberRepository memberRepository, SearchHistoryRepository searchHistoryRepository) {
        this.memberRepository = memberRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public SearchHistory addSearchHistory(String email, String searchKeyword) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        Optional<SearchHistory> searchHistoryOpt= searchHistoryRepository.findBySearchKeyword(searchKeyword);
        searchHistoryOpt.ifPresent(history -> searchHistoryRepository.delete(history));
        SearchHistory searchHistory = new SearchHistory(searchKeyword, memberOpt.get());
        return searchHistoryRepository.save(searchHistory);
    }

    @Transactional(readOnly = true)
    public Page<SearchHistory> getHistoryByMember(Member member, Pageable pageable) {
        return searchHistoryRepository.findByMember(member, pageable);
    }
}

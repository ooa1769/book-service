package com.ooa1769.bs.support.security;

import com.ooa1769.bs.member.domain.Member;
import com.ooa1769.bs.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";

    private MemberRepository memberRepository;

    public SecurityUserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        Member member = memberOpt.orElseThrow(
                () -> new UsernameNotFoundException("Not found Member with email" + email));

        return new User(member.getEmail(), member.getPassword(), member.isEnabled(), true,true,true, getAuthorities(ROLE_USER));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}

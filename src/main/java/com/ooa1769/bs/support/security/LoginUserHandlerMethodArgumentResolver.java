package com.ooa1769.bs.support.security;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        return memberOpt.orElseThrow(() -> new UnAuthorizedException("로그인이 필요합니다."));
    }
}

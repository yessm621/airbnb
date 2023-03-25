package com.airbnb.common.security;

import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Member member = memberRepository.findByEmail(email);

        if (member != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return CustomUserDetails.create(member);
        } else {
            throw new UsernameNotFoundException("can not find User : " + email);
        }
    }
}

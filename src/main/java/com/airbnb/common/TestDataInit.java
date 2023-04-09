package com.airbnb.common;

import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.LoginMethod;
import com.airbnb.member.entity.Member;
import com.airbnb.member.entity.MemberRole;
import com.airbnb.member.entity.MemberStatus;
import com.airbnb.room.AmenitiesRepository;
import com.airbnb.room.entity.Amenities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final AmenitiesRepository amenitiesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        memberRepository.save(Member.builder()
                .email("admin@gmail.com")
                .password(bCryptPasswordEncoder.encode("test123!!!"))
                .name("관리자")
                .loginMethod(LoginMethod.EMAIL)
                .superHost(true)
                .role(MemberRole.ROLE_ADMIN)
                .status(MemberStatus.ACTIVE)
                .build());

        memberRepository.save(Member.builder()
                .email("yessm621@gmail.com")
                .password(bCryptPasswordEncoder.encode("test123!!!"))
                .name("노승미")
                .loginMethod(LoginMethod.EMAIL)
                .superHost(false)
                .role(MemberRole.ROLE_USER)
                .status(MemberStatus.ACTIVE)
                .build());
    }
}

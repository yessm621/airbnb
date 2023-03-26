package com.airbnb.member;

import com.airbnb.member.dto.FindPasswordDto;
import com.airbnb.member.dto.RegisterDto;
import com.airbnb.member.entity.LoginMethod;
import com.airbnb.member.entity.Member;
import com.airbnb.member.entity.MemberRole;
import com.airbnb.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(RegisterDto dto) {
        Member entity = Member.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .loginMethod(LoginMethod.EMAIL)
                .superHost(false)
                .role(MemberRole.ROLE_USER)
                .status(MemberStatus.ACTIVE)
                .build();

        memberRepository.save(entity);
    }

    @Transactional
    public void passwordModify(FindPasswordDto dto) {
        Member findMember = memberRepository.findByEmail(dto.getEmail());
        findMember.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
    }
}

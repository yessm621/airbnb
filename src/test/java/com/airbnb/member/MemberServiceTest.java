package com.airbnb.member;

import com.airbnb.member.dto.RegisterDto;
import com.airbnb.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 성공")
    void register_success() {
        RegisterDto form = RegisterDto.builder()
                .email("yessm621@gmail.com")
                .password("asdf#@!123")
                .passwordConfirm("asdf#@!123")
                .name("nohseungmi")
                .build();

        Member member = Member.builder()
                .email(form.getEmail())
                .name(form.getName())
                .build();

        Member saveMember = memberRepository.save(member);
        assertThat(member.getEmail()).isEqualTo(saveMember.getEmail());
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입 실패")
    void email_already_register_fail() {
        Member member = Member.builder()
                .email("yessm621@gmail.com")
                .name("노승미")
                .build();
        memberRepository.save(member);

        Member newMember = Member.builder()
                .email("yessm621@gmail.com")
                .name("홍길동")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> {
            memberRepository.save(newMember);
        });
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않아 회원가입 실패")
    void password_not_match_register_fail() {

    }

    @Test
    @DisplayName("비밀번호 조건이 맞지 않아 회원가입 실패")
    void password_not_met_register_fail() {

    }

    @Test
    @DisplayName("빈 값인 경우")
    void required_value_is_Empty() {

    }

    @Test
    @DisplayName("null인 경우")
    void required_value_is_null() {

    }
}
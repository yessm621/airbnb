package com.airbnb.profile;

import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.Member;
import com.airbnb.profile.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileDto getProfileDetail(String email) {
        Member member = memberRepository.findByEmail(email);
        ProfileDto dto = ProfileDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .description(member.getDescription())
                .phone(member.getPhone())
                .birth(member.getBirthdate())
                .image(member.getProfile())
                .build();
        return dto;
    }

    @Transactional
    public void save(ProfileDto dto) {
        System.out.println("ProfileService.save");
        Member findMember = memberRepository.findByEmail(dto.getEmail());
        findMember.setDescription(dto.getDescription());
        findMember.setPhone(dto.getPhone());
        findMember.setBirthdate(dto.getBirth());
    }
}

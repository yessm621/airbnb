package com.airbnb.profile;

import com.airbnb.common.s3.S3Uploader;
import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.Member;
import com.airbnb.profile.dto.ProfileDetailDto;
import com.airbnb.profile.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private static final String DIR_NAME = "profile";

    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    public ProfileDto profileInfo(String email) {
        Member member = memberRepository.findByEmail(email);
        ProfileDto dto = ProfileDto.builder()
                .name(member.getName())
                .description(member.getDescription())
                .profile(member.getProfile())
                .build();
        return dto;
    }

    public ProfileDetailDto profileDetail(String email) {
        Member member = memberRepository.findByEmail(email);
        ProfileDetailDto dto = ProfileDetailDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .description(member.getDescription())
                .phone(member.getPhone())
                .birth(member.getBirthdate())
                .build();
        return dto;
    }

    @Transactional
    public void save(ProfileDetailDto dto) throws IOException {

        Member findMember = memberRepository.findByEmail(dto.getEmail());
        findMember.setDescription(dto.getDescription());
        findMember.setPhone(dto.getPhone());
        findMember.setBirthdate(dto.getBirth());

        if (dto.getImage() != null) {
            String path = s3Uploader.upload(dto.getImage(), DIR_NAME);
            log.info("이미지 업로드 경로={}", path);
            findMember.setProfile(path);
        }
    }
}

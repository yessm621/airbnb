package com.airbnb.common;

import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.LoginMethod;
import com.airbnb.member.entity.Member;
import com.airbnb.member.entity.MemberRole;
import com.airbnb.member.entity.MemberStatus;
import com.airbnb.room.AmenitiesRepository;
import com.airbnb.room.RoomService;
import com.airbnb.room.dto.RoomCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final AmenitiesRepository amenitiesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoomService roomService;

    @PostConstruct
    public void init() throws IOException {
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

        IntStream.range(1, 30).forEach(i -> {
            RoomCreateRequestDto dto = RoomCreateRequestDto.builder()
                    .title("숙소" + i)
                    .description("숙소 설명입니다" + i)
                    .country("나라" + i)
                    .city("도시" + i)
                    .price(70000 + i)
                    .guest(2)
                    .bedroom(2)
                    .bed(2)
                    .bath(3)
                    .roomType("PENSION")
                    .build();
            try {
                roomService.createRoom(dto, "admin@gmail.com", null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}

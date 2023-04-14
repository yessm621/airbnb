package com.airbnb.room;

import com.airbnb.common.s3.S3Uploader;
import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.Member;
import com.airbnb.room.dto.RoomCreateRequestDto;
import com.airbnb.room.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private static final String DIR_NAME = "room";

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final AmenitiesRepository amenitiesRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final HouseRuleRepository houseRuleRepository;
    private final PhotoRepository photoRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public void createRoom(RoomCreateRequestDto dto, String email, MultipartFile[] photos) throws IOException {

        Member member = memberRepository.findByEmail(email);
        Address address = new Address(dto.getZipcode(), dto.getAddress1(), dto.getAddress2());

        List<RoomAmenities> roomAmenities = getRoomAmenities(dto.getAmenities());
        List<RoomFacilities> roomFacilities = getRoomFacilities(dto.getFacilities());
        List<RoomHouseRule> houseRules = getHouseRule(dto.getHouseRules());

        Room room = Room.createRoom(address, member, dto, roomAmenities, roomFacilities, houseRules);

        if (photos != null) {
            for (MultipartFile photo : photos) {
                String path = s3Uploader.upload(photo, DIR_NAME);
                log.info("이미지 업로드 경로={}", path);
                room.setThumbnail(path);
                Photo savePhoto = Photo.builder()
                        .room(room)
                        .filename(photo.getOriginalFilename())
                        .filePath(path)
                        .build();
                photoRepository.save(savePhoto);
            }
        }

        roomRepository.save(room);
    }

    private List<RoomAmenities> getRoomAmenities(Long[] amenities) {
        List<Amenities> amenitiesList = amenitiesRepository.findAmenitiesIn(amenities);
        List<RoomAmenities> roomAmenities = amenitiesList.stream()
                .map(RoomAmenities::createRoomAmenities)
                .collect(Collectors.toList());
        return roomAmenities;
    }

    private List<RoomFacilities> getRoomFacilities(Long[] facilities) {
        List<Facilities> facilitiesList = facilitiesRepository.findFacilitiesIn(facilities);
        List<RoomFacilities> roomFacilities = facilitiesList.stream()
                .map(RoomFacilities::createRoomFacilities)
                .collect(Collectors.toList());
        return roomFacilities;
    }

    private List<RoomHouseRule> getHouseRule(Long[] houseRules) {
        List<HouseRule> houseRule = houseRuleRepository.findHouseRuleIn(houseRules);
        List<RoomHouseRule> roomHouseRules = houseRule.stream()
                .map(RoomHouseRule::createRoomHouseRule)
                .collect(Collectors.toList());
        return roomHouseRules;
    }
}

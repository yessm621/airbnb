package com.airbnb.room;

import com.airbnb.member.MemberRepository;
import com.airbnb.member.entity.Member;
import com.airbnb.room.dto.RoomCreateRequestDto;
import com.airbnb.room.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final AmenitiesRepository amenitiesRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final HouseRuleRepository houseRuleRepository;

    @Transactional
    public void createRoom(RoomCreateRequestDto dto, String email) {

        Member member = memberRepository.findByEmail(email);
        Address address = new Address(dto.getZipcode(), dto.getAddress1(), dto.getAddress2());

        List<RoomAmenities> roomAmenities = getRoomAmenities(dto.getAmenities());
        List<RoomFacilities> roomFacilities = getRoomFacilities(dto.getFacilities());
        List<RoomHouseRule> houseRules = getHouseRule(dto.getHouseRules());

        Room room = Room.createRoom(address, member, dto, roomAmenities, roomFacilities, houseRules);

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

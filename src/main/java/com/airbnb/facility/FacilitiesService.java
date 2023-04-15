package com.airbnb.facility;

import com.airbnb.facility.dto.FacilitiesDto;
import com.airbnb.room.entity.RoomFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacilitiesService {

    private final EntityManager em;

    public List<FacilitiesDto> findFacilities(Long roomId) {

        List<RoomFacilities> roomFacilities = em.createQuery(
                        "select rf from RoomFacilities rf" +
                                " join fetch rf.facilities f" +
                                " where rf.room.id=:roomId", RoomFacilities.class)
                .setParameter("roomId", roomId)
                .getResultList();

        List<FacilitiesDto> facilities = roomFacilities
                .stream().map(f -> new FacilitiesDto(f.getFacilities().getName()))
                .collect(Collectors.toList());

        return facilities;
    }
}

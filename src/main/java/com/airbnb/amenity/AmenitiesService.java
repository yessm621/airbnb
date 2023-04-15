package com.airbnb.amenity;

import com.airbnb.amenity.dto.AmenitiesDto;
import com.airbnb.room.entity.RoomAmenities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AmenitiesService {

    private final EntityManager em;

    public List<AmenitiesDto> findAmenities(Long roomId) {

        List<RoomAmenities> roomAmenities = em.createQuery(
                        "select ra from RoomAmenities ra" +
                                " join fetch ra.amenities a" +
                                " where ra.room.id=:roomId", RoomAmenities.class)
                .setParameter("roomId", roomId)
                .getResultList();

        List<AmenitiesDto> amenities = roomAmenities
                .stream().map(a -> new AmenitiesDto(a.getAmenities().getName()))
                .collect(Collectors.toList());

        return amenities;
    }
}

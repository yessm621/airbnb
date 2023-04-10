package com.airbnb.room;

import com.airbnb.room.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

    @Query("select a from Amenities a where a.id in :amenities")
    List<Amenities> findAmenitiesIn(@Param("amenities") Long[] amenities);
}

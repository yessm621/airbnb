package com.airbnb.facility;

import com.airbnb.room.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {

    @Query("select f from Facilities f where f.id in :facilities")
    List<Facilities> findFacilitiesIn(@Param("facilities") Long[] facilities);
}

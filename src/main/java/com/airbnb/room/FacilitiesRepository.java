package com.airbnb.room;

import com.airbnb.room.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {
}

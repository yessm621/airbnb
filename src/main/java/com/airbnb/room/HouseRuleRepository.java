package com.airbnb.room;

import com.airbnb.room.entity.HouseRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRuleRepository extends JpaRepository<HouseRule, Long> {
}

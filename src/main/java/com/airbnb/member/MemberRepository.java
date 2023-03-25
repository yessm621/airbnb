package com.airbnb.member;

import com.airbnb.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.lastLoginDate=current_timestamp WHERE m.email=:email")
    void updateLastLoginDate(@Param("email") String email);
}

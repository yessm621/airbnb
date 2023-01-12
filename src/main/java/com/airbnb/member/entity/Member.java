package com.airbnb.member.entity;

import com.airbnb.common.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Column(length = 100, unique = true, nullable = false, updatable = false)
    private String email;
    private String password;

    @NotNull
    @Column(length = 10)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private LoginMethod loginMethod; // EMAIL, KAKAO, GITHUB

    @Lob
    private String profile;
    private LocalDate birthdate;

    @Column(length = 10)
    private String phone;

    @Lob
    private String description;
    private LocalDateTime lastLoginDate;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean superhost;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private MemberStatus status; // ACTIVE, STOP

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private MemberGrade grade; // USER, HOST, ADMIN
}

package com.airbnb.profile.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetailDto {

    private String email;
    private String name;
    private String description;
    private String phone;
    private LocalDate birth;
    private MultipartFile image;
}

package com.airbnb.profile.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetailDto {

    private String email;
    private String name;
    private String description;
    @Pattern(regexp = "(^\\d{2,3}\\d{3,4}\\d{4}$)|", message = "핸드폰 번호의 형식과 맞지 않습니다.")
    private String phone;
    @Pattern(regexp = "(([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])))|", message = "생년월일 형식(yyyymmdd)과 맞지 않습니다")
    private String birth;
}

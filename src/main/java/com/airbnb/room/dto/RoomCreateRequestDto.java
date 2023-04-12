package com.airbnb.room.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomCreateRequestDto {

    @NotBlank(message = "숙소명을 입력해주세요.")
    private String title;
    @NotBlank(message = "숙소에 대한 설명을 입력해주세요.")
    private String description;

    @NotBlank(message = "나라를 입력해주세요.")
    private String country;
    @NotBlank(message = "도시명을 입력해주세요.")
    private String city;

    @NotBlank(message = "주소를 입력해주세요.")
    private String zipcode;
    private String address1;
    private String address2;

    @Min(value = 10000, message = "숙소 금액은 10,000원 이상이어야 합니다.")
    private int price;

    @Min(value = 1, message = "게스트 수는 1명 이상이어야 합니다.")
    private int guest;
    @Min(value = 1, message = "방 개수는 1개 이상이어야 합니다.")
    private int bedroom;
    private int bed;
    private int bath;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkIn;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOut;

    private Boolean instantBook;
    @NotBlank(message = "숙소 타입을 선택해주세요.")
    private String roomType;
    private Long[] amenities;
    private Long[] facilities;
    private Long[] houseRules;
}

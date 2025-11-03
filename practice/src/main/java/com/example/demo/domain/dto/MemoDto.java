package com.example.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //디폴트 생성자
@AllArgsConstructor //모든 생성자
@Data //toString
public class MemoDto {
//    @Min(value = 10,message = "ID는 10이상의 값부터 시작합니다.")
//    @Max(value = 65535,message = "ID의 푀대 숫자는 65535입니다.")
//    @NotNull(message= "ID는 필수 항목입니다.")
//    private Long id;
    @NotBlank(message = "TEXT는 필수 항목입니다.")
    private String text;
    @NotBlank(message = "작성자를 입력하세요")
    @Email(message = "example@example.com형식으로 입력하세요")
    private String writer;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") //현재
//    @NotNull(message = "날짜정보를 입력하세요")
//    @Future(message = "오늘날짜기준 이후 날짜를 입력하세요") //미래
//    private LocalDateTime createAt;
//
//    private LocalDate data_test;




}

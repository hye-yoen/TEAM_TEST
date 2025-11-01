package com.example.demo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompDto {
    private Long compId;
    @NotBlank(message = "이미지 필수 항목")
    private String compImg;
    @NotBlank(message = "대회 이름 필수 항목")
    private String compName;
    @NotBlank(message = "대회 정보 필수 항목")
    private String compText;
}

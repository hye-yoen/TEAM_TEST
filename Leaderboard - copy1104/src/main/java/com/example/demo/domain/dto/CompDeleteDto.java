package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompDeleteDto {
//    @NotBlank(message = "대회 이름 필수 항목")
//    private String compName;

    private Long compId;
}

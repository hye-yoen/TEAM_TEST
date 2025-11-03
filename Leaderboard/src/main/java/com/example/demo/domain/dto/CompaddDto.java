package com.example.demo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompaddDto {
    private Long compId;
    private String compImg;
    private String compName;
    private String compText;
}


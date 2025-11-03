package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private Integer pageNo;
    private Integer amount;
    private String keyword;
    private String type;
    //int 는 null 체크 불가

}

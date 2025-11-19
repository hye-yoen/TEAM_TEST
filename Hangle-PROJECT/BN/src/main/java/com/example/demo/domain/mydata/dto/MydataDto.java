package com.example.demo.domain.mydata.dto;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MydataDto {

    private Long Mydata_id;

    private Long userId;          // PK
    private String userid;        // 로그인 ID
    private String username;

    private String fileName;

    private String filePath;

    private LocalDateTime submittedAt;
}

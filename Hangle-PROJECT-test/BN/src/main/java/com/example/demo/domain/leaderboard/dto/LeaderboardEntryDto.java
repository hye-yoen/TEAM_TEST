package com.example.demo.domain.leaderboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LeaderboardEntryDto {


    private int rank; //순위

    @Schema(example = "user01")
    private String userid;

    @Schema(example = "0.9123")
    private double bestScore;

    @Schema(example = "7")
    private int submissions; // 제출 횟수

    private LocalDateTime submittedAt; //최근 제출일
}

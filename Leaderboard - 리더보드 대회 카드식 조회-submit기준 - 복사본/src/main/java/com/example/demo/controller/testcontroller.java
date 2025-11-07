package com.example.demo.controller;

import com.example.demo.domain.leaderboard.dto.LeaderboardDto;
import com.example.demo.domain.leaderboard.repository.LeaderboardRepository;
import com.example.demo.domain.leaderboard.service.LeaderboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@Slf4j
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://localhost:3000"})
public class testcontroller {

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    // 전체 조회 + 검색
    @GetMapping("")
    public List<LeaderboardDto> getAllLeaderboards(
            @RequestParam(name = "keyword", required = false) String keyword
    ) throws Exception {
        log.info("GET /api/leaderboard keyword={}", keyword);

        List<LeaderboardDto> allList = leaderboardService.getAllLeaderboard();
        List<LeaderboardDto> resultList;

        if (keyword == null || keyword.isEmpty()) {
            resultList = allList;
        } else {
            resultList = leaderboardService.searchLeaderboard(keyword);
            if (resultList.isEmpty()) {
                log.warn("검색 결과 없음 → 전체 리스트 반환");
                resultList = allList;
            }
        }

        // 대회명 리스트 정리
        List<String> compNameList = resultList.stream()
                .map(LeaderboardDto::getCompName)
                .distinct()
                .toList();

        // 정렬
        resultList = resultList.stream()
                .sorted(Comparator
                        .comparing(LeaderboardDto::getCompName, Comparator.nullsLast(String::compareTo))
                        .thenComparing(LeaderboardDto::getRank, Comparator.nullsLast(Integer::compareTo)))
                .toList();

        log.info("리더보드 반환 완료 ({}건)", resultList.size());

        return resultList;
    }
}




package com.example.demo.domain.service;

import com.example.demo.domain.dto.LeaderboardDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Leaderboard;
import com.example.demo.domain.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    public List<LeaderboardDto> getAllCompList() {
//        System.out.println("leaderboardallList");
//        return leaderboardRepository.findAll()
//                .stream()
//                .map(leaderboard -> new LeaderboardDto(
//                       leaderboard.getLeaderBoardId(),
//                        leaderboard.getCompname(),
//                        leaderboard.getUsername(),
//                        leaderboard.getBest_score(),
//                        leaderboard.getLast_submit_time(),
//                        leaderboard.getSubmit_count()
//                ))
//                .collect(Collectors.toList());

        System.out.print("leaderboared-score-list");
        return leaderboardRepository.findLeaderboardOrderByScore()
                .stream()
                .map(leaderboard -> new LeaderboardDto(
                       leaderboard.getLeaderBoardId(),
                        leaderboard.getCompname(),
                        leaderboard.getUsername(),
                        leaderboard.getBest_score(),
                        leaderboard.getLast_submit_time(),
                        leaderboard.getSubmit_count()
                ))
                .collect(Collectors.toList());
    }

      public List<LeaderboardDto> searchLeaderboard(String keyword) {
        // 레포지토리에서 다중 조건 검색
        List<Leaderboard> leads = leaderboardRepository.searchByKeyword(keyword);
        // 엔티티 → DTO 변환
        return leads.stream()
                .map(l -> new LeaderboardDto(
                        l.getLeaderBoardId(),
                        l.getCompname(),
                        l.getUsername(),
                        l.getBest_score(),
                        l.getLast_submit_time(),
                        l.getSubmit_count()
                ))
                .collect(Collectors.toList());
    }

    public Long leaderBoardAdd(LeaderboardDto dto) throws Exception{
        //dto -> entity
        Leaderboard leaderboard = Leaderboard.builder()
                .leaderBoardId(null)
                .compname(dto.getCompname())
                .best_score(dto.getBest_score())
                .last_submit_time(LocalDateTime.now())
                .submit_count(dto.getSubmit_count())
                .build();
        leaderboardRepository.save(leaderboard);
        return leaderboard.getLeaderBoardId();
    }



}

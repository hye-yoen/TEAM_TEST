package com.example.demo.domain.service;

import com.example.demo.domain.dto.LeaderboardDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Leaderboard;
import com.example.demo.domain.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    public List<LeaderboardDto> getAllCompList() {
        System.out.println("compList");
        return leaderboardRepository.findAll()
                .stream()
                .map(leaderboard -> new LeaderboardDto(
                       leaderboard.getUser_id(),
                        leaderboard.getNickname(),
                        leaderboard.getMax()
                ))
                .collect(Collectors.toList());
    }

    public Long leaderBoardAdd(LeaderboardDto dto) throws Exception{
        //dto -> entity
        Leaderboard leaderboard = Leaderboard.builder()
                .user_id(dto.getUser_id())
                .nickname(dto.getNickname())
                .max(dto.getMax())
                .build();
        leaderboardRepository.save(leaderboard);
        return leaderboard.getUser_id();
    }

    public List<LeaderboardDto> searchLeaderboard(String keyword) {
        // 레포지토리에서 다중 조건 검색
        List<Leaderboard> leads = leaderboardRepository.searchByKeyword(keyword);
        // 엔티티 → DTO 변환
        return leads.stream()
                .map(l -> new LeaderboardDto(
                        l.getUser_id(),
                        l.getNickname(),
                        l.getMax()
                ))
                .collect(Collectors.toList());
    }




}

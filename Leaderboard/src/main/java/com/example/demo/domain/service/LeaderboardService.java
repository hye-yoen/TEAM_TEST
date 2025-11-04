package com.example.demo.domain.service;

import com.example.demo.domain.dto.LeaderboardDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Leaderboard;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.CompRepository;
import com.example.demo.domain.repository.LeaderboardRepository;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private CompRepository compRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LeaderboardDto> getLeaderboardByCompetition(Long compId) {
        System.out.print("leaderboared-score-list");
        return leaderboardRepository.findByCompIdOrderByBestScoreDesc(compId)
                .stream()
                .map(l -> new LeaderboardDto(
                       l.getLeaderBoardId(),
                        l.getComp().getCompId(),
                        l.getUser().getUserId(),
                        l.getComp().getCompName(),
                        l.getUser().getUserName(),
                        l.getBest_score(),
                        l.getLast_submit_time(),
                        l.getSubmit_count()
                ))
                .collect(Collectors.toList());
    }

      public List<LeaderboardDto> searchLeaderboard(String keyword, Long compId) {
        // 레포지토리에서 다중 조건 검색
        List<Leaderboard> leads = leaderboardRepository.searchByKeyword(keyword,compId);
        // 엔티티 → DTO 변환
        return leads.stream()
                .map(l -> new LeaderboardDto(
                        l.getLeaderBoardId(),
                        l.getComp().getCompId(),
                        l.getUser().getUserId(),
                        l.getComp().getCompName(),
                        l.getUser().getUserName(),
                        l.getBest_score(),
                        l.getLast_submit_time(),
                        l.getSubmit_count()
                ))
                .collect(Collectors.toList());
    }

    public Long leaderBoardAdd(LeaderboardDto dto) throws Exception{

        //compName , userName 찾기
        Comp comp = compRepository.findById(dto.getCompId())
                .orElseThrow(() -> new IllegalArgumentException("대회 정보 없음. ID=" + dto.getCompId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보 없음. ID=" + dto.getUserId()));

        //dto -> entity
        Leaderboard leaderboard = Leaderboard.builder()
                .leaderBoardId(null)
                .comp(comp)    //Service에서 DTO 변환할 때 꺼내씀
                .user(user)
                .best_score(dto.getBest_score())
                .last_submit_time(LocalDateTime.now())
                .submit_count(dto.getSubmit_count())
                .build();
        leaderboardRepository.save(leaderboard);
        return leaderboard.getLeaderBoardId();
    }




}

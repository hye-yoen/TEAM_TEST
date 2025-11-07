package com.example.demo.domain.leaderboard.service.impl;

import com.example.demo.domain.competition.entity.Submission;
import com.example.demo.domain.competition.repository.SubmissionRepository;
import com.example.demo.domain.leaderboard.dto.LeaderboardEntryDto;
import com.example.demo.domain.leaderboard.repository.LeaderboardRepository;
import com.example.demo.domain.leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final SubmissionRepository submissionRepository;

    @Override
    public List<LeaderboardEntryDto> leaderboard(Long competitionId) {
        // 사용자별 best score 계산
        List<Submission> subs = submissionRepository.findByCompetitionIdOrderByScoreDesc(competitionId);
        //대회 목록 내림차순
        Map<String, List<Submission>> byUser = subs.stream()
                .collect(Collectors.groupingBy(Submission::getUserid));

        //각 사용자의 ID(userid)를 기준으로 제출 목록을 그룹화함.
        List<LeaderboardEntryDto> rows = byUser.entrySet().stream()
                .map(e -> {

                    // 사용자별 최고 점수 찾기
                    double best = e.getValue().stream().mapToDouble(Submission::getScore).max().orElse(0.0);
                    //사용자별 최근 제출 시간
                    LocalDateTime lastSubmittedAt = e.getValue().stream()
                            .map(Submission::getSubmittedAt)
                            .max(LocalDateTime::compareTo)
                            .orElse(null);

                    return new AbstractMap.SimpleEntry<>(e.getKey(), new Object[]{best,lastSubmittedAt});
                })
                .sorted((a,b) -> Double.compare((double)b.getValue()[0], (double)a.getValue()[0])) // 내림차순
                .map(new java.util.function.Function<AbstractMap.SimpleEntry<String, Object[]>, LeaderboardEntryDto>() {
                    int rank = 0; //실제순위
                    double lastScore = Double.NaN; //이전 사람의 점수 기억 → 같으면 순위 유지
                    int seen = 0; //전체 몇 번재 사람인지

                    @Override
                    public LeaderboardEntryDto apply(AbstractMap.SimpleEntry<String , Object[]> entry) {
                        seen++;
                        double score = (double)entry.getValue()[0];
                        LocalDateTime submittedAt = (LocalDateTime) entry.getValue()[1];
                        if (Double.compare(score, lastScore) != 0) { // 동점 동일 순위
                            rank = seen;
                            lastScore = score;
                        }
                        // 제출 횟수 계산 + 결과리스트 반환
                        int submissions = (int) subs.stream().filter(s -> s.getUserid().equals(entry.getKey())).count();
                        return LeaderboardEntryDto.builder()
                                .rank(rank)
                                .userid(entry.getKey())
                                .bestScore(score)
                                .submissions(submissions)
                                .submittedAt(submittedAt)
                                .build();
                    }
                }).toList();

        return rows;
    }




    @Override //위에서 만든거 확용 내순위 조회
    public LeaderboardEntryDto myRank(Long competitionId, String userid) {
        List<LeaderboardEntryDto> board = leaderboard(competitionId);
        return board.stream()
                .filter(e -> e.getUserid().equals(userid))
                .findFirst()
                .orElse(LeaderboardEntryDto.builder()
                        .rank(0).userid(userid).bestScore(0).submissions(0).build());
    }
}

package com.example.demo.domain.repository;

import com.example.demo.domain.dto.LeaderboardDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {


    //순위 조회
    @Query("SELECT l FROM Leaderboard l ORDER BY l.best_score DESC")
    List<Leaderboard> findLeaderboardOrderByScore();


    //서치
    @Query("""
    SELECT l FROM Leaderboard l
    WHERE LOWER(COALESCE(STR(l.leaderBoardId), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(l.compname, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(l.username, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(STR(l.best_score), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(STR(l.last_submit_time), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(STR(l.submit_count), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Leaderboard> searchByKeyword(@Param("keyword") String keyword);

}

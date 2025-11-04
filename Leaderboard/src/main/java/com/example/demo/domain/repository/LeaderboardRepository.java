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
//    @Query("SELECT l FROM Leaderboard l ORDER BY l.best_score DESC")
//    List<Leaderboard> findLeaderboardOrderByScore();

//    @Query("SELECT l FROM Leaderboard l WHERE l.compname = :compname ORDER BY l.best_score DESC")
//    List<Leaderboard> findByCompnameOrderByBestScoreDesc(String compname);

    @Query("SELECT l FROM Leaderboard l WHERE l.comp.compId = :compId ORDER BY l.best_score DESC")
    List<Leaderboard> findByCompIdOrderByBestScoreDesc(@Param("compId") Long compId);


    //서치
//    @Query("""
//    SELECT l FROM Leaderboard l
//    WHERE LOWER(COALESCE(STR(l.comp.compName), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
//    OR LOWER(COALESCE(l.user.userName, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
//    """)
//    List<Leaderboard> searchByKeyword(@Param("keyword") String keyword);

    @Query("""
        SELECT l FROM Leaderboard l
        WHERE l.comp.compId = :compId
        AND LOWER(l.user.userName) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Leaderboard> searchByKeyword(@Param("keyword") String keyword, @Param("compId") Long compId);
}

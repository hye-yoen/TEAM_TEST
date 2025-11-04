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

    @Query("""
    SELECT l FROM Leaderboard l
    WHERE LOWER(COALESCE(STR(l.user_id), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(l.nickname, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(COALESCE(STR(l.max), '')) LIKE LOWER(CONCAT('%', :keyword, '%'))""")
    List<Leaderboard> searchByKeyword(@Param("keyword") String keyword);

}

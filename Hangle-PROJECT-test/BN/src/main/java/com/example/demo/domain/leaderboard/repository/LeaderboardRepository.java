package com.example.demo.domain.leaderboard.repository;

import com.example.demo.domain.leaderboard.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {

}

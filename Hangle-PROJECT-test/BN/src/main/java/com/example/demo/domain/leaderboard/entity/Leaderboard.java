package com.example.demo.domain.leaderboard.entity;

import com.example.demo.domain.competition.entity.Submission;
import com.example.demo.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaderBoardId;

    private int rank;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    @OneToOne
    @JoinColumn(name="id")
    private Submission submission;

}

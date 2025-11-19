package com.example.demo.domain.mydata.entity;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mydata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mydata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Mydata_id;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user; //유저명, 유저 아이디

    private String fileName;

    private String filePath;

    private LocalDateTime submittedAt;
}

package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comp")
@Data
@Builder
public class Comp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compId;
    @Column(length = 25668)
    private String compImg;
    @Column(length = 100)
    private String compName;
    @Column(length = 5000)
    private String compText;



//    대회 피일 업로드 나중에


}


package com.example.demo.domain.competition.repository;

import com.example.demo.domain.competition.entity.Competition;
import com.example.demo.domain.competition.entity.CompetitionCSVSave;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompetitionCSVSaveRepository extends JpaRepository<CompetitionCSVSave, Long> {
}

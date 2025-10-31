package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompRepository extends JpaRepository<Comp,Long> {

    Optional<Comp> findByCompName(String compName);

}

package com.example.demo.domain.mydata.repository;

import com.example.demo.domain.mydata.entity.Mydata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MydataRepository extends JpaRepository<Mydata, Long> {
}

package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Comp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompRepository extends JpaRepository<Comp,Long> {

    Optional<Comp> findByCompName(String compName);

    //search
//    @Query("SELECT c FROM Comp c " +
//            "WHERE (:compName IS NULL OR c.compName LIKE %:compName%) " +
//            "AND (:compText IS NULL OR c.compText LIKE %:compText%) " +
//            "AND (:compImg IS NULL OR c.compImg LIKE %:compImg%)")
//    List<Comp> findByMultipleFields(@Param("compName") String compName,
//                                    @Param("compText") String compText,
//                                    @Param("compImg") String compImg);


}

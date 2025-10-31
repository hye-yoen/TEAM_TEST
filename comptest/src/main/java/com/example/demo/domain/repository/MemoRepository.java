package com.example.demo.domain.repository;


import com.example.demo.domain.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {
    //메서드 명명법
    //JPQL(SQL문 작성)

    Page<Memo> findByTextContaining(String keyword, Pageable pageable);
    //메모에 있는 text //키워드 페이지에 전달할 전달값

}

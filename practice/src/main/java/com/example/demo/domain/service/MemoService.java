package com.example.demo.domain.service;



import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.dto.PageDto;
import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;


    public Long memoRegisstrarion2(MemoDto dto) throws Exception{
        //dto -> entity
        Memo memo = Memo.builder()
                .id(null)
                .text(dto.getText())
                .writer(dto.getWriter())
                .createAt(LocalDateTime.now())
                .build();
        memoRepository.save(memo);
        return memo.getId();
    }


    @Transactional(rollbackFor = Exception.class)
    public Page<Memo> listMemo(PageDto pageDto) throws SQLException{
        //PageNo
        //pageBlock : 15

        int pageNo =0;
        int amount =10;
        if(pageDto.getPageNo()!=null){
            pageNo = pageDto.getPageNo();
        }
        if(pageDto.getAmount()!=null){
            amount = pageDto.getAmount();
        }
        Pageable pageable = PageRequest.of(pageNo,amount, Sort.by("id").descending());
        //요청할 페이지 넘버, 페이지당 들어갈 데이터 수 ,내림차순
        Page<Memo> page = memoRepository.findAll(pageable);

        //페이지 메타 확인
        System.out.println("현재 페이지 번호"+page.getNumber());
        System.out.println("한 페이지에 표시할 건수"+page.getSize());
        System.out.println("총게시물 갯수"+page.getTotalElements());
        System.out.println("총페이지 갯수"+page.getTotalPages());
        System.out.println("첫번째 페이지인지 여부"+page.isFirst());
        System.out.println("다음 페이지가 있는지 여부"+page.hasNext());
        System.out.println("이전 페이지가 있는지 여부"+page.hasPrevious());


        return page;
    }


}

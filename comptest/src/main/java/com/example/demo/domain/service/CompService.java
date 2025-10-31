package com.example.demo.domain.service;

import com.example.demo.domain.dto.CompDeleteDto;
import com.example.demo.domain.dto.CompDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.repository.CompRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompService {


    @Autowired
    private CompRepository compRepository;

    public Long compRegisstrarion1(CompDto dto) throws Exception{
        //dto -> entity
       Comp comp = Comp.builder()
               .compId(null)
               .compImg(dto.getCompImg())
               .compName(dto.getCompName())
               .compText(dto.getCompText())
                .build();
        compRepository.save(comp);
        return comp.getCompId();
    }

    public Long compdeleteByname(CompDeleteDto dto)throws Exception{
        String compName = dto.getCompName();

        Comp comp = compRepository.findByCompName(compName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 회사를 찾을 수 없습니다: " + compName));

        compRepository.delete(comp);
        return comp.getCompId();

    }


}

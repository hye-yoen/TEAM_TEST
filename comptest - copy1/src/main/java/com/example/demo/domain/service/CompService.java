package com.example.demo.domain.service;

import com.example.demo.domain.dto.CompDeleteDto;
import com.example.demo.domain.dto.CompDetailDto;
import com.example.demo.domain.dto.CompDto;
import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.Comp;
import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.repository.CompRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

//    public Long compdeleteByname(CompDeleteDto dto)throws Exception{
//        String compName = dto.getCompName();
//
//        Comp comp = compRepository.findByCompName(compName)
//                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 회사를 찾을 수 없습니다: " + compName));
//
//        compRepository.delete(comp);
//        return comp.getCompId();
//
//    }
    public void deleteCompById(Long compId) {
        compRepository.deleteById(compId);
    }

    public CompDto findById(Long compId){
        return  compRepository.findById(compId)
                .map(comp -> new CompDto(
                        comp.getCompId(),
                        comp.getCompImg(),
                        comp.getCompName(),
                        comp.getCompText()
                ))
                .orElse(null);

    }


    public List<CompDto> getAllCompList() {
        System.out.println("compList");
        return compRepository.findAll()
                .stream()
                .map(comp -> new CompDto(
                        comp.getCompId(),
                        comp.getCompImg(),
                        comp.getCompName(),
                        comp.getCompText()
                ))
                .collect(Collectors.toList());
    }

    public List<CompDto> searchComp(String compName, String compText, String compImg) {
        // 레포지토리에서 다중 조건 검색
        List<Comp> comps = compRepository.findByMultipleFields(compName, compText, compImg);
        // 엔티티 → DTO 변환
        return comps.stream()
                .map(comp -> new CompDto(
                        comp.getCompId(),
                        comp.getCompImg(),
                        comp.getCompName(),
                        comp.getCompText()
                ))
                .collect(Collectors.toList());
    }

    public CompDetailDto getCompDetail(Long compId) {
        Comp comp = compRepository.findById(compId)
                .orElseThrow(() -> new RuntimeException("대회를 찾을 수 없습니다. ID: " + compId));

        return new CompDetailDto(
                comp.getCompId(),
                comp.getCompImg(),
                comp.getCompName(),
                comp.getCompText()
        );
    }



}




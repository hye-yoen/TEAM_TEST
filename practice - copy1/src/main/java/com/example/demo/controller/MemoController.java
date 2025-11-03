package com.example.demo.controller;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.dto.PageDto;
import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.service.MemoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @ExceptionHandler(Exception.class)
    public String exception_handler(Exception e){
        log.error("MemoController's Exception..." + e);
        return "memo/error";
    }



    @GetMapping("/add")
    public void add_memo_get()throws Exception {
        log.info("GET /memo/add...");
    }
    @PostMapping("/add")
    public String add_memo_post(@Valid MemoDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception
    {  //BindingResult => 검증 오류 처리 및 보관 객체
        log.info("POST /memo/add..." +dto);
        //파라미터
        //입력값 검증(데이터)
        log.info("유효성 오류 발생여부 : "+bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){
                log.info("Error Field: "+error.getField()+ "Error Message : "+error.getDefaultMessage());
                model.addAttribute(error.getField(),error.getDefaultMessage());
                //키 ,값 형태로 model 전달 // 에러가 있는 경우 dto에 있는 @NotBlanck를 메세지를 전달
            }
            return "memo/add";
        }
        //throw new NullPointerException("예외발생");
        //서비스 요청
//        boolean isAdded = memoService.memoRegistration(dto); //메모서비스 연결 memo.. 로전달
        Long insertedId = memoService.memoRegisstrarion2(dto);
        if(insertedId != null)
            redirectAttributes.addFlashAttribute("message","메모등록완료!" + insertedId);
        //메세지라는 키로 전달

        //뷰로 이동 -> Domaon.Common.Service
        return insertedId != null? "redirect:/":"memo/add";
    }

    @GetMapping("/list")
    public void list(
//            @RequestParam(value = "pageNo",defaultValue = "0") int pageNo, //기본 값=페이지
//            @RequestParam(value = "amount",defaultValue = "10") int amount //한번에 표시할 사이즈
            PageDto pageDto,
            Model model
    )throws  Exception
    {
        log.info("GET /memo/list...pageDto : "+ pageDto);
        //유효성 체크 (생략)
        //서비스 실행
        //1. pageAble 요청객체
        //2. service로 넘기기
        Page<Memo> page = memoService.listMemo(pageDto);
        //뷰로 이동(+데이터)
        model.addAttribute("page",page); //현재 페이지가 뷰에 있는 경우
        model.addAttribute("list",page.getContent());
    }



}

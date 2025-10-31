package com.example.demo.controller;

import com.example.demo.domain.dto.CompDeleteDto;
import com.example.demo.domain.dto.CompDto;
import com.example.demo.domain.service.CompService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;

@Controller
@Slf4j
@RequestMapping("/comp")
public class CompController {

    @Autowired
    private CompService compService;

    @GetMapping("/add")
    public void comp_add ()throws Exception {
        log.info("GET /comp/add...");
    }
    @PostMapping("/add")
    public String comp_add_post(@Valid CompDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception{
       if(bindingResult.hasErrors()){
           for(FieldError error : bindingResult.getFieldErrors())
           {
               log.info("Error Field : "+ error.getField());
               model.addAttribute(error.getField(),error.getDefaultMessage());
           }
           return "comp/add";
       }

       //서비스 연결
        Long insertedId = compService.compRegisstrarion1(dto);
       if(insertedId != null){
           redirectAttributes.addFlashAttribute("message" ,"대회 등록 완료!" + insertedId );
       }
        return insertedId != null? "redirect:/comp/list":"comp/add";
    }



    @GetMapping("/delete")
    public void comp_delete ()throws Exception {
        log.info("GET /comp/delete...");
    }
    @PostMapping("/delete")
    public String comp_delete_post(@Valid CompDeleteDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception{
        //BindingResult => 검증 오류 처리 및 보관 객체
        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors())
            {
                log.info("Error Field : "+ error.getField());
                model.addAttribute(error.getField(),error.getDefaultMessage());
            }
            return "comp/delete";
        }
        Long deletedId = null; // 삭제된 ID를 담을 변수

        //서비스 연결
        deletedId = compService.compdeleteByname(dto);
        if(deletedId != null){
            redirectAttributes.addFlashAttribute("message" ,"대회 삭제 완료!"  );
        }
        return deletedId != null? "redirect:/comp/list":"comp/delete";
    }




    @GetMapping("/list")
    public void list_get(){
        log.info("list");
    }

}

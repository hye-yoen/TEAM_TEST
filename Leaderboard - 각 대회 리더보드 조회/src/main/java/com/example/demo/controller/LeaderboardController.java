package com.example.demo.controller;

import com.example.demo.domain.dto.LeaderboardDto;
import com.example.demo.domain.entity.Leaderboard;
import com.example.demo.domain.repository.LeaderboardRepository;
import com.example.demo.domain.service.LeaderboardService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/leaderboard")
@Slf4j
//@CrossOrigin(origins = {"http://127.0.0.1:3000","http://localhost:3000"})
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("")
    public String LeaderboardAllList(
            @RequestParam Long compId,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model
    )throws Exception{
        log.info("leaderboard list");

        List<LeaderboardDto> list = leaderboardService.getLeaderboardByCompetition(compId);
        System.out.println(list);
        if( keyword == null || keyword.isEmpty()){

            model.addAttribute("leaderboard", list);
            model.addAttribute("compId", compId);
            return "leaderboard";
        }
        try {
            // 서비스에서 다중 조건 검색 가능하도록 메소드 구현 필요
            List<LeaderboardDto> resultList = leaderboardService.searchLeaderboard(keyword,compId);
            System.out.println(resultList.size());
            if (resultList.isEmpty()) {
                model.addAttribute("errorMessage", "검색 결과가 없습니다.");
                model.addAttribute("leaderboard", list);
            }

            model.addAttribute("leaderboard", resultList);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "검색 중 오류가 발생했습니다.");
            model.addAttribute("leaderboard", list);
        }
        model.addAttribute("compId", compId);

        return "leaderboard";
    }

//    @GetMapping("/add")
//    public void comp_add ()throws Exception {
//        log.info("GET /comp/add...");
//    }
//    @PostMapping("/add")
//    public String comp_add_post(
//            LeaderboardDto dto
//    ) throws Exception {
//        //서비스 연결
//        Long insertedId = leaderboardService.leaderBoardAdd(dto);
//        return insertedId != null ? "redirect:/leaderboard" : "leaderboard/add";
//    }

}
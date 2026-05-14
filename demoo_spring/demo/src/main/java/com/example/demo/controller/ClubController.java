package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ClubService;

@Controller
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    // 첫 화면: 동아리 선택
    @GetMapping("/")
    public String clubList(Model model) {
        model.addAttribute("clubs", clubService.getClubList());
        return "club-list";
    }

    // 동아리 내부 메뉴
    @GetMapping("/club")
    public String clubMenu(@RequestParam String clubName, Model model) {
        model.addAttribute("clubName", clubName);
        return "club-menu";
    }

    // 동아리 소개
    @GetMapping("/club/info")
    public String clubInfo(@RequestParam String clubName, Model model) {
        model.addAttribute("club", clubService.getClub(clubName));
        return "info";
    }

    // 회원 목록
    @GetMapping("/club/members")
    public String members(@RequestParam String clubName, Model model) {
        model.addAttribute("clubName", clubName);
        model.addAttribute("members", clubService.getMembers(clubName));
        return "members";
    }

    // 가입 신청 화면
    @GetMapping("/club/apply")
    public String applyForm(@RequestParam String clubName, Model model) {
        model.addAttribute("clubName", clubName);
        return "apply";
    }

    // 가입 신청 처리
    @PostMapping("/club/apply")
    public String apply(
            @RequestParam String clubName,
            @RequestParam String studentId,
            @RequestParam String name,
            Model model) {

        boolean result = clubService.applyToClub(clubName, studentId, name);

        model.addAttribute("clubName", clubName);
        model.addAttribute("result", result);

        return "apply-result";
    }

    // 가입 승인 화면
    @GetMapping("/club/approve")
    public String approveForm(@RequestParam String clubName, Model model) {
        model.addAttribute("clubName", clubName);
        return "approve-password";
    }

    // 비밀번호 확인 후 승인 목록
    @PostMapping("/club/approve/list")
    public String approveList(
            @RequestParam String clubName,
            @RequestParam String password,
            Model model) {

        boolean passwordResult = clubService.checkClubPassword(clubName, password);

        if (!passwordResult) {
            model.addAttribute("clubName", clubName);
            model.addAttribute("message", "비밀번호가 틀렸습니다.");
            return "approve-password";
        }

        model.addAttribute("clubName", clubName);
        model.addAttribute("applications", clubService.getApplications(clubName));

        return "approve";
    }

    // 수락 처리
    @PostMapping("/club/approve")
    public String approve(
            @RequestParam String clubName,
            @RequestParam String studentId,
            Model model) {

        clubService.approveApplication(clubName, studentId);

        model.addAttribute("clubName", clubName);
        model.addAttribute("applications", clubService.getApplications(clubName));

        return "approve";
    }
}
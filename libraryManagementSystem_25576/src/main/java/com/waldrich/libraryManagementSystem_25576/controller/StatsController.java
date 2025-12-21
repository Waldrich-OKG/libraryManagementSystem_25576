package com.waldrich.libraryManagementSystem_25576.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.dto.OverviewStats;
import com.waldrich.libraryManagementSystem_25576.dto.UserStats;
import com.waldrich.libraryManagementSystem_25576.model.UserPrincipal;
import com.waldrich.libraryManagementSystem_25576.service.StatsService;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "http://localhost:5173")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/librarian")
    public OverviewStats getOverviewStats() {
        return statsService.getOverviewStats();
    }

    @GetMapping("/user")
    public UserStats getUserStats(@AuthenticationPrincipal UserPrincipal user) {
        return statsService.getUserStats(user.getId());
    }

    
}

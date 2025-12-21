package com.waldrich.libraryManagementSystem_25576.dto;

import lombok.Data;

@Data
public class OverviewStats {
    
    private long totalBooks;
    private long totalUsers;
    private long finesCollected;
    private long borrowings;
    private long reservations;
}

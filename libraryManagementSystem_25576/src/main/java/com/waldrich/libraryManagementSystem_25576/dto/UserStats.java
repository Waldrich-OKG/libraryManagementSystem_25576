package com.waldrich.libraryManagementSystem_25576.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStats {
    
    private long borrowings;
    private long reservations;
    private long fines;

}

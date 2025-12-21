package com.waldrich.libraryManagementSystem_25576.dto;

import com.waldrich.libraryManagementSystem_25576.model.Fines;

import lombok.Data;

@Data
public class FineDTO {
    
    private Long fineId;
    private Double amount;
    private String issueDate;
    private String fineStatus;
    private Long borrowingId;
    private String userEmail;

    public FineDTO(Fines fine) {
        this.fineId = fine.getFineId();
        this.amount = fine.getAmount();
        this.issueDate = fine.getIssueDate().toString();
        this.fineStatus = fine.getFineStatus().name();
        this.borrowingId = fine.getBorrowing().getBorrowingId();
        this.userEmail = fine.getBorrowing().getUser().getEmail();
    }

}

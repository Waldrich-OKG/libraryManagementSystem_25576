package com.waldrich.libraryManagementSystem_25576.model;

import java.util.Date;

import com.waldrich.libraryManagementSystem_25576.enums.FineStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Fines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    private double amount;

    private Date issueDate;

    @Enumerated(EnumType.STRING)
    private FineStatus fineStatus;

    @OneToOne
    @JoinColumn(name = "borrowing_id")
    private Borrowings borrowing;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    
}

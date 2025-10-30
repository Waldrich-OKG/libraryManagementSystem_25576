package com.waldrich.libraryManagementSystem_25576.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Borrowings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingId;

    private Date borrowDate;

    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @OneToOne(mappedBy = "borrowing", cascade = CascadeType.ALL)
    private Fines fine;

    
}

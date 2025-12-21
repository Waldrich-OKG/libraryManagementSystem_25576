package com.waldrich.libraryManagementSystem_25576.model;

import java.util.List;

import com.waldrich.libraryManagementSystem_25576.enums.BookCategory;
import com.waldrich.libraryManagementSystem_25576.enums.BookStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Books {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String imagePath;

    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private String language;

    private String yearOfPublication;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Borrowings> borrowings;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservations> reservations;
}

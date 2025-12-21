package com.waldrich.libraryManagementSystem_25576.dto;

import com.waldrich.libraryManagementSystem_25576.model.Borrowings;

import lombok.Data;

@Data
public class BorrowingDTO {
    
    private Long borrowingId;
    private String bookTitle;
    private String userEmail;
    private String borrowDate;
    private String returnDate;

    public BorrowingDTO(Borrowings borrowing) {
        this.borrowingId = borrowing.getBorrowingId();
        this.bookTitle = borrowing.getBook().getTitle();
        this.userEmail = borrowing.getUser().getEmail();
        this.borrowDate = borrowing.getBorrowDate().toString();
        this.returnDate = borrowing.getReturnDate() != null ? borrowing.getReturnDate().toString() : null;
    }


}

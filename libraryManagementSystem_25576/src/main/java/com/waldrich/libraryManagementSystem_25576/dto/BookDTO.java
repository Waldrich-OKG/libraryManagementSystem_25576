package com.waldrich.libraryManagementSystem_25576.dto;

import com.waldrich.libraryManagementSystem_25576.model.Books;

import lombok.Data;

@Data
public class BookDTO {
    
    private Long bookId;
    private String imagePath;
    private String title;
    private String author;
    private String category;
    private String language;
    private String yearOfPublication;
    private String bookStatus;

    public BookDTO(Books book) {
        this.bookId = book.getBookId();
        this.imagePath = book.getImagePath();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.category = book.getCategory().toString();
        this.language = book.getLanguage();
        this.yearOfPublication = book.getYearOfPublication();
        this.bookStatus = book.getBookStatus().toString();
    }

}

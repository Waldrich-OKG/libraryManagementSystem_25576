package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.waldrich.libraryManagementSystem_25576.enums.BookCategory;
import com.waldrich.libraryManagementSystem_25576.model.Books;

public interface BooksRepository extends JpaRepository<Books,Long> {

    Page<Books> findAll(Pageable pageable);

    List<Books> findByTitleContaining(String query);

    List<Books> findByAuthorContaining(String author);

    List<Books> findByCategory(BookCategory category);

    Page<Books> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLanguageContainingIgnoreCaseOrYearOfPublicationContainingIgnoreCaseOrBookStatusContainingIgnoreCase(
        String title,
        String author,
        String category,
        String language,
        String year,
        String status,
        Pageable pageable
    );

    @Query("SELECT b FROM Books b WHERE " +
       "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(b.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(b.language) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(b.yearOfPublication) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(b.bookStatus) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Books> searchBooks(@Param("keyword") String keyword, Pageable pageable);
}

package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.enums.BookCategory;
import com.waldrich.libraryManagementSystem_25576.model.Books;

public interface BooksRepository extends JpaRepository<Books,Long> {

    Page<Books> findAll(Pageable pageable);

    List<Books> findByTitleContaining(String query);

    List<Books> findByAuthorContaining(String author);

    List<Books> findByCategory(BookCategory category);
    
}

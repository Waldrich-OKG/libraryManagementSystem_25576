package com.waldrich.libraryManagementSystem_25576.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.model.Borrowings;

public interface BorrowingsRepository extends JpaRepository<Borrowings,Long> {

    Page<Borrowings> findByUser_UserId(Long userId, Pageable pageable);


    Page<Borrowings> findAll(Pageable pageable);

    long countByUser_UserId(Long userId);

    Page<Borrowings> findByBorrowingId(Long borrowingId, Pageable pageable);
    Page<Borrowings> findByBook_TitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Borrowings> findByUser_EmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Borrowings> findByBorrowingIdAndUser_UserId(Long borrowingId, Long userId, Pageable pageable);
    Page<Borrowings> findByBook_TitleContainingIgnoreCaseAndUser_UserId(String title, Long userId, Pageable pageable);

    
}

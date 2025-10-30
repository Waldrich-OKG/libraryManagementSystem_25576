package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.model.Borrowings;

public interface BorrowingsRepository extends JpaRepository<Borrowings,Long> {

    List<Borrowings> findByUser_UserId(Long userId);


    Page<Borrowings> findAll(Pageable pageable);
    
}

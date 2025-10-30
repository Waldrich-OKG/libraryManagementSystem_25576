package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.model.Fines;

public interface FinesRepository extends JpaRepository<Fines,Long> {

    List<Fines> findByUser_UserId(Long userId);

    Page<Fines> findAll(Pageable pageable);
    
}

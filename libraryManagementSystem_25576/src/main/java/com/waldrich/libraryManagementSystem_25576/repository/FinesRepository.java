package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.enums.FineStatus;
import com.waldrich.libraryManagementSystem_25576.model.Fines;

public interface FinesRepository extends JpaRepository<Fines,Long> {

    List<Fines> findByUser_UserId(Long userId);

    Page<Fines> findAll(Pageable pageable);

    long countByUser_UserId(Long userId);

    Page<Fines> findByBorrowing_User_UserId(Long userId, Pageable pageable);

    Page<Fines> findByFineIdAndBorrowing_User_UserId(Long fineId, Long userId, Pageable pageable);
    Page<Fines> findByAmountAndBorrowing_User_UserId(Double amount, Long userId, Pageable pageable);
    Page<Fines> findByFineStatusAndBorrowing_User_UserId(FineStatus status, Long userId, Pageable pageable);
    
}

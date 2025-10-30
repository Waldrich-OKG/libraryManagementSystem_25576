package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.enums.ReservationStatus;
import com.waldrich.libraryManagementSystem_25576.model.Reservations;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {

    List<Reservations> findByReservationDate(Date reservationDate);

    List<Reservations> findByReservationStatus(ReservationStatus reservationStatus);

    List<Reservations> findByUser_UserId(Long userId);

    Page<Reservations> findAll(Pageable pageable);
    
}

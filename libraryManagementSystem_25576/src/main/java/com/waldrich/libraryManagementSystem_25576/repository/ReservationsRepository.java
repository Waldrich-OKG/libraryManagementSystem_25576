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

    Page<Reservations> findByUser_UserId(Long userId, Pageable pageable);

    Page<Reservations> findAll(Pageable pageable);

    long countByUser_UserId(Long userId);

    Page<Reservations> findByReservationId(Long reservationId, Pageable pageable);
    Page<Reservations> findByBook_TitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Reservations> findByUser_EmailContainingIgnoreCase(String email, Pageable pageable);
    Page<Reservations> findByStatus(ReservationStatus status, Pageable pageable);

    Page<Reservations> findByReservationIdAndUser_UserId(Long reservationId, Long userId, Pageable pageable);
    Page<Reservations> findByBook_TitleContainingIgnoreCaseAndUser_UserId(String title, Long userId, Pageable pageable);
    Page<Reservations> findByStatusAndUser_UserId(ReservationStatus status, Long userId, Pageable pageable);

    
}

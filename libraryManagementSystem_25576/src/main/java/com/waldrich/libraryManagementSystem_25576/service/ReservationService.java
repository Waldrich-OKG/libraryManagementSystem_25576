package com.waldrich.libraryManagementSystem_25576.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import com.waldrich.libraryManagementSystem_25576.enums.ReservationStatus;
import com.waldrich.libraryManagementSystem_25576.model.Reservations;
import com.waldrich.libraryManagementSystem_25576.repository.ReservationsRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationsRepository reservationsRepository;

    public Reservations saveReservation(Reservations reservation) {
        return reservationsRepository.save(reservation);
    }

    public Page<Reservations> getAllReservations(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reservationsRepository.findAll(pageable);
    }

    public Reservations getReservationById(Long reservationId) {
        return reservationsRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId + " not found"));
    }

    public List<Reservations> getReservationsByDate(Date reservationDate) {
        return reservationsRepository.findByReservationDate(reservationDate);
    }

    public List<Reservations> getReservationsByStatus(ReservationStatus reservationStatus) {
        return reservationsRepository.findByReservationStatus(reservationStatus);
    }

    public List<Reservations> getReservationsByUserId(Long userId) {
        return reservationsRepository.findByUser_UserId(userId);
    }

    public Reservations updateReservation(Long reservationId, Reservations updatedReservation) {
        Reservations existingReservation = reservationsRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId + " not found"));
        existingReservation.setReservationDate(updatedReservation.getReservationDate());
        existingReservation.setReservationStatus(updatedReservation.getReservationStatus());
        return reservationsRepository.save(existingReservation);
    }

    public void deleteReservation(Long reservationId) {
        reservationsRepository.deleteById(reservationId);
    }


}

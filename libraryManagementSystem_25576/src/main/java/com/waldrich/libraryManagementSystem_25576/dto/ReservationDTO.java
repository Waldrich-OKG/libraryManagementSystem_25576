package com.waldrich.libraryManagementSystem_25576.dto;

import com.waldrich.libraryManagementSystem_25576.model.Reservations;

import lombok.Data;

@Data
public class ReservationDTO {
    
    private Long reservationId;
    private String bookTitle;
    private String userEmail;
    private String reservationDate;
    private String status;

    public ReservationDTO(Reservations reservation) {
        this.reservationId = reservation.getReservationId();
        this.bookTitle = reservation.getBook().getTitle();
        this.userEmail = reservation.getUser().getEmail();
        this.reservationDate = reservation.getReservationDate().toString();
        this.status = reservation.getReservationStatus().name();
    }

}

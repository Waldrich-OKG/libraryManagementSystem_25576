package com.waldrich.libraryManagementSystem_25576.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.dto.ReservationDTO;
import com.waldrich.libraryManagementSystem_25576.enums.ReservationStatus;
import com.waldrich.libraryManagementSystem_25576.model.Reservations;
import com.waldrich.libraryManagementSystem_25576.model.UserPrincipal;
import com.waldrich.libraryManagementSystem_25576.service.ReservationService;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<Reservations> registerReservation(@RequestBody Reservations reservation) {
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ReservationDTO>> getAllReservations(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "reservationId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(reservationService.getAllReservations(page, size, sortBy, direction));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public Page<ReservationDTO> getUserReservations(@RequestParam int page, @RequestParam int size, UserPrincipal principal) {
        Long userId = principal.getId();
        Pageable pageable = PageRequest.of(page, size);
        return reservationService.getUserReservations(userId, pageable);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','REGULAR')")
    public Page<ReservationDTO> searchReservations(
        @RequestParam String column,
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        UserPrincipal principal
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (principal.getRole().equals("REGULAR")) {
            return reservationService.searchReservationsForUser(principal.getId(), column, keyword, pageable);
        }
        return reservationService.searchReservations(column, keyword, pageable);
    }

    @GetMapping("/date/{reservationDate}")
    public ResponseEntity<List<Reservations>> getReservationsByDate(@PathVariable Date reservationDate) {
        return ResponseEntity.ok(reservationService.getReservationsByDate(reservationDate));
    }

    @GetMapping("/status/{reservationStatus}")
    public ResponseEntity<List<Reservations>> getReservationsByStatus(@PathVariable ReservationStatus reservationStatus) {
        return ResponseEntity.ok(reservationService.getReservationsByStatus(reservationStatus));
    }

    @GetMapping("/one/{reservationId}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PostMapping("/update/{reservationId}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable Long reservationId, @RequestBody Reservations updatedReservation) {
        return ResponseEntity.ok(reservationService.updateReservation(reservationId, updatedReservation));
    }

    @PostMapping("/delete/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }
    
}

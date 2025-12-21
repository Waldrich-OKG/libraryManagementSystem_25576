package com.waldrich.libraryManagementSystem_25576.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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

import com.waldrich.libraryManagementSystem_25576.dto.FineDTO;
import com.waldrich.libraryManagementSystem_25576.model.Fines;
import com.waldrich.libraryManagementSystem_25576.model.UserPrincipal;
import com.waldrich.libraryManagementSystem_25576.service.FinesService;
import com.waldrich.libraryManagementSystem_25576.service.UsersService;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/fines")
@CrossOrigin("*")
public class FineController {

    @Autowired
    private FinesService finesService;

    @Autowired
    private UsersService userService;

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fines> saveFine(@RequestBody Fines fine) {
        return ResponseEntity.ok(finesService.saveFine(fine));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<FineDTO>> getAllFines(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "fineId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(finesService.getAllFines(page, size, sortBy, direction));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('REGULAR')")
    public Page<FineDTO> getUserFines(@RequestParam int page, @RequestParam int size, UserPrincipal principal) {
        Long userId = userService.getUserIdFromPrincipal(principal);
        Pageable pageable = PageRequest.of(page, size);
        return finesService.getUserFines(userId, pageable);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Fines>> getFinesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(finesService.getFinesByUserId(userId));
    }

    @GetMapping("/one/{fineId}")
    public ResponseEntity<Fines> getFineById(@PathVariable Long fineId) {
        return ResponseEntity.ok(finesService.getFineById(fineId));
    }

    @PostMapping(value = "/update/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fines> updateFine(@PathVariable Long id, @RequestBody Fines updatedFine) {
        return ResponseEntity.ok(finesService.updateFine(id, updatedFine));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFine(@PathVariable Long id) {
        finesService.deleteFine(id);
        return ResponseEntity.ok().build();
    }
    
}

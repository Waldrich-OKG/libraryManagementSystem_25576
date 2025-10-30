package com.waldrich.libraryManagementSystem_25576.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.model.Borrowings;
import com.waldrich.libraryManagementSystem_25576.service.BorrowingsService;

@RestController
@RequestMapping("/borrowings")
@CrossOrigin("*")
public class BorrowingController {
    
    @Autowired
    private BorrowingsService borrowingsService;

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Borrowings> saveBorrowing(@RequestBody Borrowings borrowing) {
        return ResponseEntity.ok(borrowingsService.saveBorrowing(borrowing));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Borrowings>> getAllBorrowings(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "borrowingId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(borrowingsService.getAllBorrowings(page, size, sortBy, direction));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Borrowings>> getBorrowingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowingsService.getBorrowingsByUserId(userId));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Borrowings> getBorrowingById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingsService.getBorrowingById(id));
    }

    @PostMapping(value = "/update/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Borrowings> updateBorrowing(@PathVariable Long id, @RequestBody Borrowings updatedBorrowing) {
        return ResponseEntity.ok(borrowingsService.updateBorrowing(id, updatedBorrowing));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        borrowingsService.deleteBorrowing(id);
        return ResponseEntity.ok().build();
    }


}

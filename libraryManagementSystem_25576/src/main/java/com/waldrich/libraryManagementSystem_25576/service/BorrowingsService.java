package com.waldrich.libraryManagementSystem_25576.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.model.Borrowings;
import com.waldrich.libraryManagementSystem_25576.repository.BorrowingsRepository;

@Service
public class BorrowingsService {
    
    @Autowired
    private BorrowingsRepository borrowingsRepository;

    public Borrowings saveBorrowing(Borrowings borrowing) {
        return borrowingsRepository.save(borrowing);
    }

    public Page<Borrowings> getAllBorrowings(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return borrowingsRepository.findAll(pageable);
    }

    public Borrowings getBorrowingById(Long id) {
        return borrowingsRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing with id " + id + " not found"));
    }

    public List<Borrowings> getBorrowingsByUserId(Long userId) {
        return borrowingsRepository.findByUser_UserId(userId);
    }

    public Borrowings updateBorrowing(Long id, Borrowings updatedBorrowing) {
        Borrowings existingBorrowing = borrowingsRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing with id " + id + " not found"));
        existingBorrowing.setBorrowDate(updatedBorrowing.getBorrowDate());
        existingBorrowing.setReturnDate(updatedBorrowing.getReturnDate());
        return borrowingsRepository.save(existingBorrowing);
    }

    public void deleteBorrowing(Long id) {
        borrowingsRepository.deleteById(id);
    }


}

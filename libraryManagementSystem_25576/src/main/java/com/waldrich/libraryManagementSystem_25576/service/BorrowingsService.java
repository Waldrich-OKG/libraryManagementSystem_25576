package com.waldrich.libraryManagementSystem_25576.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.dto.BorrowingDTO;
import com.waldrich.libraryManagementSystem_25576.model.Borrowings;
import com.waldrich.libraryManagementSystem_25576.repository.BorrowingsRepository;

@Service
public class BorrowingsService {
    
    @Autowired
    private BorrowingsRepository borrowingsRepository;

    public Borrowings saveBorrowing(Borrowings borrowing) {
        return borrowingsRepository.save(borrowing);
    }

    public Page<BorrowingDTO> getAllBorrowings(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return borrowingsRepository.findAll(pageable).map(BorrowingDTO::new);
    }

    public Page<BorrowingDTO> getUserBorrowings(Long userId, Pageable pageable) {
        return borrowingsRepository.findByUser_UserId(userId, pageable).map(BorrowingDTO::new);
    }

    public Page<BorrowingDTO> searchBorrowings(String column, String keyword, Pageable pageable) {
    switch (column) {
        case "borrowingId":
            return borrowingsRepository.findByBorrowingId(Long.valueOf(keyword), pageable).map(BorrowingDTO::new);
        case "bookTitle":
            return borrowingsRepository.findByBook_TitleContainingIgnoreCase(keyword, pageable).map(BorrowingDTO::new);
        case "userEmail":
            return borrowingsRepository.findByUser_EmailContainingIgnoreCase(keyword, pageable).map(BorrowingDTO::new);

        default:
            return Page.empty(pageable);
    }
}


    public Borrowings getBorrowingById(Long id) {
        return borrowingsRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing with id " + id + " not found"));
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

    public Page<BorrowingDTO> searchBorrowingsForUser(Long id, String column, String keyword, Pageable pageable) {
        switch (column) {
        case "borrowingId":
            return borrowingsRepository.findByBorrowingIdAndUser_UserId(Long.valueOf(keyword), id, pageable)
                                      .map(BorrowingDTO::new);
        case "bookTitle":
            return borrowingsRepository.findByBook_TitleContainingIgnoreCaseAndUser_UserId(keyword, id, pageable)
                                      .map(BorrowingDTO::new);
        default:
            return Page.empty(pageable);
    }

    }


}

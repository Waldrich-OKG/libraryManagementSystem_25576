package com.waldrich.libraryManagementSystem_25576.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import com.waldrich.libraryManagementSystem_25576.dto.FineDTO;
import com.waldrich.libraryManagementSystem_25576.model.Fines;
import com.waldrich.libraryManagementSystem_25576.repository.FinesRepository;

@Service
public class FinesService {
    
    @Autowired
    private FinesRepository finesRepository;

    public Fines saveFine(Fines fine) {
        return finesRepository.save(fine);
    }

    public Page<FineDTO> getAllFines(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return finesRepository.findAll(pageable).map(FineDTO::new);
    }

    public Page<FineDTO> getUserFines(Long userId, Pageable pageable) {
        return finesRepository.findByBorrowing_User_UserId(userId, pageable).map(FineDTO::new);
    }

    public List<Fines> getFinesByUserId(Long userId) {
        return finesRepository.findByUser_UserId(userId);
    }

    public Fines getFineById(Long id) {
        return finesRepository.findById(id).orElseThrow(() -> new RuntimeException("Fine with id " + id + " not found"));
    }

    public Fines updateFine(Long id, Fines updatedFine) {
        Fines existingFine = finesRepository.findById(id).orElseThrow(() -> new RuntimeException("Fine with id " + id + " not found"));
        existingFine.setAmount(updatedFine.getAmount());
        existingFine.setIssueDate(updatedFine.getIssueDate());
        existingFine.setFineStatus(updatedFine.getFineStatus());
        return finesRepository.save(existingFine);
    }

    public void deleteFine(Long id) {
        finesRepository.deleteById(id);
    }


}

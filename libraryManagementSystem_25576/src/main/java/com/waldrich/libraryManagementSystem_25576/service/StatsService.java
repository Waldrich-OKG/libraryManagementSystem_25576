package com.waldrich.libraryManagementSystem_25576.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.dto.OverviewStats;
import com.waldrich.libraryManagementSystem_25576.dto.UserStats;
import com.waldrich.libraryManagementSystem_25576.repository.BooksRepository;
import com.waldrich.libraryManagementSystem_25576.repository.BorrowingsRepository;
import com.waldrich.libraryManagementSystem_25576.repository.FinesRepository;
import com.waldrich.libraryManagementSystem_25576.repository.ReservationsRepository;
import com.waldrich.libraryManagementSystem_25576.repository.UsersRepository;

@Service
public class StatsService {
    
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BorrowingsRepository borrowingsRepository;

    @Autowired
    private FinesRepository finesRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public OverviewStats getOverviewStats() {

        OverviewStats stats = new OverviewStats();
        stats.setTotalBooks(booksRepository.count());
        stats.setTotalUsers(usersRepository.count());
        stats.setFinesCollected(finesRepository.count());
        stats.setBorrowings(borrowingsRepository.count());
        stats.setReservations(reservationsRepository.count());
        return stats;
    }

    public UserStats getUserStats(Long userId) {
        long borrowings = borrowingsRepository.countByUser_UserId(userId);
        long reservations = reservationsRepository.countByUser_UserId(userId);
        long fines = finesRepository.countByUser_UserId(userId);

        return new UserStats(borrowings, reservations, fines);
    }

}

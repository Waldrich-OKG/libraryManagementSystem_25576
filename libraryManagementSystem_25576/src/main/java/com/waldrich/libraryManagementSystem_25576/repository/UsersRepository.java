package com.waldrich.libraryManagementSystem_25576.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.model.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByEmail(String email);

    Page<Users> findByLocationName(String name, Pageable pageable);

    Page<Users> findByLocationCode(String code, Pageable pageable);

    Page<Users> findAll(Pageable pageable);

    Users findByEmail(String email);
}

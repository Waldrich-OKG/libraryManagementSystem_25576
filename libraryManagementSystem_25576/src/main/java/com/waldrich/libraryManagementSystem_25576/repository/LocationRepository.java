package com.waldrich.libraryManagementSystem_25576.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waldrich.libraryManagementSystem_25576.enums.LocationType;
import com.waldrich.libraryManagementSystem_25576.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {

    boolean existsByCode(String code);

    Optional<Location> findByCode(String parentCode);

    Optional<Location> findByName(String name);

    List<Location> findByType(LocationType type);

    void deleteByCode(String code);
    
}

package com.waldrich.libraryManagementSystem_25576.model;

import java.util.List;

import com.waldrich.libraryManagementSystem_25576.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Borrowings> borrowings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Fines> fines;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservations> reservations;
}

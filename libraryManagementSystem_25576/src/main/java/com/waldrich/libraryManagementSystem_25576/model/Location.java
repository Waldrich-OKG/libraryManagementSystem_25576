package com.waldrich.libraryManagementSystem_25576.model;

import java.util.List;

import com.waldrich.libraryManagementSystem_25576.enums.LocationType;

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
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(unique = true)
    private String code;

    private String name;

    @Enumerated(EnumType.STRING)
    private LocationType type;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Location parent;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Users> users;

    
}

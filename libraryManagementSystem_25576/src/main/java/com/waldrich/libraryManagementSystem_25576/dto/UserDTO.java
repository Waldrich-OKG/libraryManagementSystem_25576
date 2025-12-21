package com.waldrich.libraryManagementSystem_25576.dto;

import com.waldrich.libraryManagementSystem_25576.model.Users;

import lombok.Data;

@Data
public class UserDTO {
    
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String locationName;


    public UserDTO(Users user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole().name();
        this.locationName = user.getLocation() != null ? user.getLocation().getName() : null;
    }


}

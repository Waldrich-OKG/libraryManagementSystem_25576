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

import com.waldrich.libraryManagementSystem_25576.model.Location;
import com.waldrich.libraryManagementSystem_25576.model.Users;
import com.waldrich.libraryManagementSystem_25576.service.UsersService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Users>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "userId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(usersService.getAllUsers(page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @PostMapping(value = "/update/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        return ResponseEntity.ok(usersService.updateUser(id, updatedUser));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/in/{name}")
    public ResponseEntity<Page<Users>> getUsersByLocationName(
        @PathVariable String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "userId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(usersService.getUsersByLocationName(name, page, size, sortBy, direction));
    }

    @GetMapping("/by-location/code/{code}")
    public ResponseEntity<Page<Users>> getUsersByLocationCode(
        @PathVariable String code,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "userId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(usersService.getUsersByLocationCode(code, page, size, sortBy, direction));
    }

    @GetMapping("/{userId}/location")
    public ResponseEntity<Location> getUserLocation(@PathVariable Long userId) {
        return usersService.getUserLocation(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/locations/provinces")
    public ResponseEntity<List<Location>> getAllProvinces() {
        return ResponseEntity.ok(usersService.getAllProvinces());
    }




    
}

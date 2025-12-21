package com.waldrich.libraryManagementSystem_25576.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.dto.UserDTO;
import com.waldrich.libraryManagementSystem_25576.enums.LocationType;
import com.waldrich.libraryManagementSystem_25576.enums.UserRole;
import com.waldrich.libraryManagementSystem_25576.exceptionsHandler.EmailAlreadyTakenException;
import com.waldrich.libraryManagementSystem_25576.exceptionsHandler.UserNotFoundException;
import com.waldrich.libraryManagementSystem_25576.model.Location;
import com.waldrich.libraryManagementSystem_25576.model.UserPrincipal;
import com.waldrich.libraryManagementSystem_25576.model.Users;
import com.waldrich.libraryManagementSystem_25576.repository.LocationRepository;
import com.waldrich.libraryManagementSystem_25576.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LocationRepository locationRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Users saveUser(Users user){
        if(usersRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyTakenException(user.getEmail());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Page<UserDTO> getAllUsers(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return usersRepository.findAll(pageable).map(UserDTO::new);
    }

    public Page<UserDTO> searchUsers(String column, String keyword, Pageable pageable) {
    switch (column) {
        case "userId":
            return usersRepository.findByUserId(Long.valueOf(keyword), pageable).map(UserDTO::new);
        case "firstName":
            return usersRepository.findByFirstNameContainingIgnoreCase(keyword, pageable).map(UserDTO::new);
        case "lastName":
            return usersRepository.findByLastNameContainingIgnoreCase(keyword, pageable).map(UserDTO::new);
        case "email":
            return usersRepository.findByEmailContainingIgnoreCase(keyword, pageable).map(UserDTO::new);
        case "role":
            return usersRepository.findByRole(UserRole.valueOf(keyword.toUpperCase()), pageable).map(UserDTO::new);
        default:
            return Page.empty(pageable);
    }
}

    public Users getUserById(Long id){
        return usersRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public Users updateUser(Long id,Users updatedUser){
        Users currentUser = usersRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        if(!currentUser.getEmail().equalsIgnoreCase(updatedUser.getEmail()) && usersRepository.existsByEmail(updatedUser.getEmail())){
            throw new EmailAlreadyTakenException(updatedUser.getEmail());
        }

        if(Objects.nonNull(updatedUser.getFirstName()) && !updatedUser.getFirstName().trim().isEmpty()){
            currentUser.setFirstName(updatedUser.getFirstName());
        }

        if(Objects.nonNull(updatedUser.getLastName()) && !updatedUser.getLastName().trim().isEmpty()){
            currentUser.setLastName(updatedUser.getLastName());
        }

        if(Objects.nonNull(updatedUser.getEmail()) && !updatedUser.getEmail().trim().isEmpty()){
            currentUser.setEmail(updatedUser.getEmail());
        }

        if(Objects.nonNull(updatedUser.getPassword()) && !updatedUser.getPassword().trim().isEmpty()){
            currentUser.setPassword(updatedUser.getPassword());
        }

        return usersRepository.save(currentUser);
    }

    public void deleteUser(Long id){
        Users user = usersRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        usersRepository.delete(user);
    }

    public Page<Users> getUsersByLocationName(String name, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return usersRepository.findByLocationName(name, pageable);
    }

    public Page<Users> getUsersByLocationCode(String code, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return usersRepository.findByLocationCode(code, pageable);
    }

    public Optional<Location> getUserLocation(Long userId) {
        return usersRepository.findById(userId)
                .map(Users::getLocation);
    }

    public List<Location> getAllProvinces() {
        return locationRepository.findByType(LocationType.PROVINCE);
    }

    public Long getUserIdFromPrincipal(UserPrincipal principal) {
        return principal.getId();
    }


}

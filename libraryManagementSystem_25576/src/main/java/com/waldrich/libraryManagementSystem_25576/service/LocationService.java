package com.waldrich.libraryManagementSystem_25576.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.model.Location;
import com.waldrich.libraryManagementSystem_25576.repository.LocationRepository;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;

    public String saveParent(Location location) {
        if(!locationRepository.existsByCode(location.getCode())){
            locationRepository.save(location);
            return "Location saved successfully";
        }
        return "Location already exists";
    }

    public String saveProvinceOrDistrictOrSectorOrCellOrVillage(String parentCode, Location location) {
        if(parentCode != null){
            Optional<Location> parent = locationRepository.findByCode(parentCode);
            if(parent.isPresent()){
                location.setParent(parent.get());
                if(!locationRepository.existsByCode(location.getCode())){
                    locationRepository.save(location);
                    return "Child saved successfully";
                }
                return "Child already exists";
            } else{
                return "Parent with this code not found";
            }
            
        } else {
            if(!locationRepository.existsByCode(location.getCode())){
                locationRepository.save(location);
                return "Parent saved successfully";
            }else{
                return "Parent already exists";
            }
        }
    }

    public Location getLocationByCode(String code) {
        return locationRepository.findByCode(code).get();
    }

    public Optional<Location> getProvinceBySectorCode(String code){
        return locationRepository.findByCode(code);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id).get();
    }

    public Location updateLocation(String code,Location location) {
        return locationRepository.save(location);
    }

    public String deleteLocationByCode(String code) {
        locationRepository.deleteByCode(code);
        return "Location deleted successfully";
    }

}

package com.waldrich.libraryManagementSystem_25576.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.model.Location;
import com.waldrich.libraryManagementSystem_25576.service.LocationService;

@RestController
@RequestMapping("/locations")
@CrossOrigin("*")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLocation(@RequestBody Location location) {
        String response = locationService.saveParent(location);
        if(response.equals("Location saved successfully")){
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/registerParentOrChild", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProvinceOrDistrictOrSectorOrCellOrVillage(@RequestParam(required =  false) String parentCode, @RequestBody Location location){

        String response = locationService.saveProvinceOrDistrictOrSectorOrCellOrVillage(parentCode, location);
        if(response.equals("Child saved successfully")){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else if(response.equals("Child already exists")){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }else if(response.equals("Parent with this code not found")){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else if(response.equals("Parent saved successfully")){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> getLocationByCode(@PathVariable String code){
        return ResponseEntity.ok(locationService.getLocationByCode(code));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Long id){
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<?> updateLocation(@PathVariable String code, @RequestBody Location location){
        return ResponseEntity.ok(locationService.updateLocation(code, location));
    }

    @PutMapping("/delete/{code}")
    public ResponseEntity<?> deleteLocationByCode(@PathVariable String code){
        return ResponseEntity.ok(locationService.deleteLocationByCode(code));
    }

    @GetMapping(value = "/findByCode")
    public ResponseEntity<?> findProvinceByDistrictCode(@RequestParam String code){
        Optional<Location> parent = locationService.getProvinceBySectorCode(code);

        if(parent.isPresent()){
            return new ResponseEntity<>(parent.get().getParent().getParent(), HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("Parent not found",HttpStatus.NOT_FOUND);
        }
    }


}

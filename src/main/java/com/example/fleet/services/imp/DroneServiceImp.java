package com.example.fleet.services.imp;

import com.example.fleet.entities.Drone;
import com.example.fleet.repositories.DroneRepository;
import com.example.fleet.response.ApiResponse;
import com.example.fleet.services.DroneServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneServiceImp implements DroneServices {
    private final DroneRepository droneRepository;

    public DroneServiceImp(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }


    @Override
    public ResponseEntity<?> createDrone(Drone drone) {
        try{
            Optional<Drone> existingDrone = droneRepository.findBySerialNumber(drone.getSerialNumber());

            if (existingDrone.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "error", "Serial number already exists"), HttpStatus.BAD_REQUEST);
            }
            drone.setState("IDLE");
            droneRepository.save(drone);
            return new ResponseEntity<>(new ApiResponse(true, "success", "DRONE CREATED SUCCESSFULLY"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, "error", "Failed to process shipping: " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

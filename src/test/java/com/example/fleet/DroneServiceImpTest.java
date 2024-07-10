package com.example.fleet;

import com.example.fleet.entities.Drone;
import com.example.fleet.repositories.DroneRepository;
import com.example.fleet.response.ApiResponse;
import com.example.fleet.services.imp.DroneServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DroneServiceImpTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneServiceImp droneService;

    private Drone drone;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        drone = new Drone();
        drone.setId(1L);
        drone.setSerialNumber("123ABC");
        drone.setDroneModel("Model X");
        drone.setWeightLimit(400);
        drone.setBatteryCapacity(100);
        drone.setState("IDLE");
        drone.setCreatedAt(LocalDateTime.now());
        drone.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testCreateDroneSuccess() {
        when(droneRepository.save(any(Drone.class))).thenReturn(drone);
        when(droneRepository.findBySerialNumber(any(String.class))).thenReturn(Optional.empty());

        ResponseEntity<?> response = droneService.createDrone(drone);
        ApiResponse apiResponse = (ApiResponse) response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("DRONE CREATED SUCCESSFULLY", apiResponse.getStatus());
        assertEquals("success", apiResponse.getMessage());
    }

    @Test
    public void testCreateDroneFailure() {
        when(droneRepository.findBySerialNumber(any(String.class))).thenReturn(Optional.of(drone));

        ResponseEntity<?> response = droneService.createDrone(drone);
        ApiResponse apiResponse = (ApiResponse) response.getBody();

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Serial number already exists", apiResponse.getStatus());
        assertEquals("error", apiResponse.getMessage());
    }
}

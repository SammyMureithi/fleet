package com.example.fleet.services;

import com.example.fleet.entities.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface DroneServices {
    ResponseEntity<?> createDrone(Drone drone);

}

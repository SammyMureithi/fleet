package com.example.fleet.controllers;

import com.example.fleet.entities.Drone;
import com.example.fleet.services.DroneServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/drone")
public class DroneController {
    private final DroneServices droneServices;

    @Autowired
    public DroneController(DroneServices droneServices) {
        this.droneServices = droneServices;
    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createDrone(@Valid @RequestBody Drone drone){
        return droneServices.createDrone(drone);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Acceptable");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}

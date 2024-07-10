package com.example.fleet.repositories;

import com.example.fleet.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone,Long > {
    Optional<Drone> findBySerialNumber(String serialNumber);
}

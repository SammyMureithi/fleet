package com.example.fleet.database;

import com.example.fleet.entities.Drone;
import com.example.fleet.repositories.DroneRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DroneRepository droneRepository;

    public DatabaseSeeder(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedDrones();
    }

    private void seedDrones() {
        if (droneRepository.count() == 0) {
            Drone drone1 = new Drone(null, "SN123456", "LightWeight", 150.0, 100.0, "IDLE", LocalDateTime.now(), LocalDateTime.now());
            Drone drone2 = new Drone(null, "SN654321", "HeavyWeight", 300.0, 80.0, "ACTIVE", LocalDateTime.now(), LocalDateTime.now());
            droneRepository.saveAll(List.of(drone1,drone2));

        }
    }
}

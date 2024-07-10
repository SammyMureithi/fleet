package com.example.fleet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="drone")
public class Drone {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    // sereialnumber
    @NotBlank(message = "Serial number is mandatory")
    @Column(name = "serial_number", unique = true)
    @Size(max = 100, message = "Serial number must be 100 characters or less")
    private  String serialNumber;

    // drone model
    @NotBlank(message = "Model is mandatory")
    private String droneModel;

    public Drone() {
    }

    //weight
    @Positive(message = "Weight must be positive")
    @Max(value = 500, message = "Weight cannot be more than 500")
    private double weightLimit;

    //batteryCapacity
    @Positive(message = "Battery must be positive")
    private double batteryCapacity;

    //state
    @NotBlank(message = "State is mandatory")
    private  String state;

    @Setter
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public @NotBlank(message = "Serial number is mandatory") @Size(max = 100, message = "Serial number must be 100 characters or less") String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NotBlank(message = "Serial number is mandatory") @Size(max = 100, message = "Serial number must be 100 characters or less") String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public @NotBlank(message = "Model is mandatory") String getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(@NotBlank(message = "Model is mandatory") String droneModel) {
        this.droneModel = droneModel;
    }

    @Positive(message = "Weight must be positive")
    @Max(value = 500, message = "Weight cannot be more than 500")
    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(@Positive(message = "Weight must be positive") @Max(value = 500, message = "Weight cannot be more than 500") double weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Positive(message = "Battery must be positive")
    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(@Positive(message = "Battery must be positive") double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public @NotBlank(message = "State is mandatory") String getState() {
        return state;
    }

    public void setState(@NotBlank(message = "State is mandatory") String state) {
        this.state = state;
    }

    public Drone(Long id, String serialNumber, String droneModel, double weightLimit, double batteryCapacity, String state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.droneModel = droneModel;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}

package com.example.fleet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name="medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_\\- ]+$", message = "Name must only contain letters, numbers, underscores, hyphens, and spaces")
    private String name;


    @NotBlank(message = "Code is mandatory")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must only contain uppercase letters, underscores, and numbers")
    private String code;

    @NotBlank(message = "Image is mandatory")
    private String image;

    @Positive(message = "Weight must be positive")
    private int weight;

    public @NotBlank(message = "Name is mandatory") @Pattern(regexp = "^[a-zA-Z0-9_\\- ]+$", message = "Name must only contain letters, numbers, underscores, hyphens, and spaces") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") @Pattern(regexp = "^[a-zA-Z0-9_\\- ]+$", message = "Name must only contain letters, numbers, underscores, hyphens, and spaces") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Code is mandatory") @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must only contain uppercase letters, underscores, and numbers") String getCode() {
        return code;
    }

    public void setCode(@NotBlank(message = "Code is mandatory") @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must only contain uppercase letters, underscores, and numbers") String code) {
        this.code = code;
    }

    public @NotBlank(message = "Image is mandatory") String getImage() {
        return image;
    }

    public void setImage(@NotBlank(message = "Image is mandatory") String image) {
        this.image = image;
    }

    @Positive(message = "Weight must be positive")
    public int getWeight() {
        return weight;
    }

    public void setWeight(@Positive(message = "Weight must be positive") int weight) {
        this.weight = weight;
    }

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Medication(Long id, String name, String code, String image, int weight, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.image = image;
        this.weight = weight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Medication(Long id) {
        this.id = id;
    }
}

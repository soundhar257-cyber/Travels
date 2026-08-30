package com.soundhar.travelportal.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "packages")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;

    @Column(length = 1000)
    private String description;

    private BigDecimal price;

    private Integer durationDays;

    private String imageUrl;

    public TravelPackage() {}

    public TravelPackage(String destination, String description, BigDecimal price, Integer durationDays, String imageUrl) {
        this.destination = destination;
        this.description = description;
        this.price = price;
        this.durationDays = durationDays;
        this.imageUrl = imageUrl;
    }

    // --- Getters and setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

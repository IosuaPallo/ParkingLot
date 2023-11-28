package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    private String licensePlate;

    @Basic
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String license_plate) {
        this.licensePlate = license_plate;
    }

    private String parkingSpot;

    @Basic
    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parking_spot) {
        this.parkingSpot = parking_spot;
    }

    private User owner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, insertable = false, updatable = false)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

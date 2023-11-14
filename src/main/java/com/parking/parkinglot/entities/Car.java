package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
public class Car {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


    private String license_plate;

    @Basic
    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    private String parking_spot;

    @Basic
    public String getParking_spot() {
        return parking_spot;
    }

    public void setParking_spot(String parking_spot) {
        this.parking_spot = parking_spot;
    }

    private User owner;

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

package com.parking.parkinglot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

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
    @Size(min=3, max=100)
    @Column(unique = true,nullable = false,length = 100)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String license_plate) {
        this.licensePlate = license_plate;
    }

    private String parkingSpot;

    @Size(min=3, max=100)
    @Column(unique = true,nullable = false,length = 100)
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


    CarPhoto photo;

    @OneToOne(mappedBy = "car",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public CarPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(CarPhoto photo) {
        this.photo = photo;
    }
}

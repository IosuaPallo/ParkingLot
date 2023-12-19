package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "carphoto")
public class CarPhoto {
    private Long id;

    private String filename;
    private String fileType;
    private byte[] fileContent;
    private Car car;

    public CarPhoto() {
    }

    public CarPhoto(Long id, String filename, String fileType, byte[] fileContent, Car car) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileContent = fileContent;
        this.car = car;
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

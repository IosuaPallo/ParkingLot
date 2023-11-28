package com.parking.parkinglot.common;

public class UserDTO {

    Long id;
    String email;
    String username;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

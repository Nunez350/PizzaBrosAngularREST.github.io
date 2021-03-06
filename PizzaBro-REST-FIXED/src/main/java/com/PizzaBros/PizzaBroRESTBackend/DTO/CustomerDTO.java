package com.PizzaBros.PizzaBroRESTBackend.DTO;

import javax.validation.constraints.NotNull;

public class CustomerDTO {
    
    private long customerId;
    @NotNull
    private String firstname;
    
    @NotNull
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    
    @NotNull
    private String address;
    
    
    private int points;
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    
    public String getFirstName() {
        return firstname;
    }
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}
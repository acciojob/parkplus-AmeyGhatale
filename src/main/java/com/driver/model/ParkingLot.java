package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String address;

    @OneToMany
    private List<Spot> spotList;

    // No-Args Constructor

    public ParkingLot() {
        spotList = new ArrayList<>();
    }

    // All-Args Constructor

    public ParkingLot(int id, String name, String address, List<Spot> spotList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.spotList = spotList;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }
}
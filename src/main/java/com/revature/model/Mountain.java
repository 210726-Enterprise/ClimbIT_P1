package com.revature.model;

import java.io.Serializable;

public class Mountain extends BaseModel implements Serializable {

    Integer id;
    String name;
    Integer meters;
    Integer feet;
    String range;
    String location;

    public Mountain() {
    }

    public Mountain(Integer id, String name, Integer meters, Integer feet, String range, String location) {
        this.id = id;
        this.name = name;
        this.meters = meters;
        this.feet = feet;
        this.range = range;
        this.location = location;
    }

    public Mountain(String name, Integer meters, Integer feet, String range, String location) {
        this.name = name;
        this.meters = meters;
        this.feet = feet;
        this.range = range;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMeters() {
        return meters;
    }

    public void setMeters(Integer meters) {
        this.meters = meters;
    }

    public Integer getFeet() {
        return feet;
    }

    public void setFeet(Integer feet) {
        this.feet = feet;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "mountain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meters=" + meters +
                ", feet=" + feet +
                ", range='" + range + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

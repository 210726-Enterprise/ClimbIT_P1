package com.revature.model;

import java.io.Serializable;
import java.math.BigInteger;

public class Climber extends BaseModel implements Serializable {

    BigInteger id;
    String first;
    String last;

    public Climber(BigInteger id, String first, String last) {
        this.id = id;
        this.first = first;
        this.last = last;
    }

    public Climber() {
    }

    public Climber(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "climber{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}

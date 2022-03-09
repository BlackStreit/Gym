package com.example.lab5.Classes;

public class Hall {
    private int hallId;
    private String hallName;

    public Hall(int hallId, String hallName) {
        this.hallId = hallId;
        this.hallName = hallName;
    }

    public Hall() {
        this.hallId = 0;
        this.hallName = "hallName";
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }


}

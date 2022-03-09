package com.example.lab5.Classes;

public class Staff {
    private int scheduleId;
    private int staffId;
    private String address;
    private String phoneNumber;
    private int workExperience;
    private int salsry;
    private String name;
    private String patronymic;
    private String surname;

    public Staff(int scheduleId, int staffId, String address, String phoneNumber, int workExperience, int salsry, String name, String patronymic, String surname) {
        this.scheduleId = scheduleId;
        this.staffId = staffId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workExperience = workExperience;
        this.salsry = salsry;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
    }

    public Staff() {
        this.scheduleId = 0;
        this.staffId = 0;
        this.address = "address";
        this.phoneNumber = "phoneNumber";
        this.workExperience = 0;
        this.salsry = 0;
        this.name = "name";
        this.patronymic = "patronymic";
        this.surname = "surname";
    }
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public int getSalsry() {
        return salsry;
    }

    public void setSalsry(int salsry) {
        this.salsry = salsry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

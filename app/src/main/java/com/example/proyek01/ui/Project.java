package com.example.proyek01.ui;

public class Project {
    int id;
    String name;
    String address;
    String startDate;
    String endDate;

    public Project(int id, String name, String address, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if(this.endDate == null){
            return "Aktif";
        } else {
            return "Selesai \n" + endDate;
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

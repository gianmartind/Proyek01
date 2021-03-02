package com.example.proyek01.projectMain;

public class ProjectDetails {
    int id;
    String name;
    String address;
    String start_date;
    String end_date;
    long materialCost;
    long otherCost;
    long paymentCost;

    public ProjectDetails(int id, String name, String address, String start_date, String end_date, long materialCost, long otherCost, long paymentCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.start_date = start_date;
        this.end_date = end_date;
        this.materialCost = materialCost;
        this.otherCost = otherCost;
        this.paymentCost = paymentCost;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public long getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(long materialCost) {
        this.materialCost = materialCost;
    }

    public long getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(long otherCost) {
        this.otherCost = otherCost;
    }

    public long getPaymentCost() { return paymentCost; }

    public void setPaymentCost(long paymentCost) { this.paymentCost = paymentCost; }
}

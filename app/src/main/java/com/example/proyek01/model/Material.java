package com.example.proyek01.model;

public class Material {
    int id;
    String name;
    String store;
    int price;
    String date;
    int projectId;

    public Material(int id, String name, String store, int price, String date, int projectId) {
        this.id = id;
        this.name = name;
        this.store = store;
        this.price = price;
        this.date = date;
        this.projectId = projectId;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

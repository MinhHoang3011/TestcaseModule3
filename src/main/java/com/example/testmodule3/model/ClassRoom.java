package com.example.testmodule3.model;

public class ClassRoom {
    int id;
    String name;

    public ClassRoom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClassRoom() {
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
}

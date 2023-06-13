package com.example.journal.newEntry;

// This class will hold the data for the object
public class Dashboard {
    // properties
    private long id = -1L;
    private String name = "";
    private String createdAt = "";

    // constructor for each property
    public Dashboard(long id, String name, String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    // default constructor
    public Dashboard() {
    }

    // to string method necessary to print the properties into a single string

    // getters and setters for each property
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

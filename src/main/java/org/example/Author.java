package org.example;

public class Author {
    private int id;
    private String name;
    private String country;

    public Author(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    public Author(){}

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

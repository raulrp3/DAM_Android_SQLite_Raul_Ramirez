package com.example.alumno_fp.sqlite;

public class Place {
    private String id;
    private String place;
    private String country;

    public Place(String id, String place, String country) {
        this.id = id;
        this.place = place;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

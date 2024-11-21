package com.mgbooking.client.DTO;

public class ListFlightDto {
    private int id;
    private String name;
    private String imageUrl;
    private String Country;

    public ListFlightDto(int id, String name, String imageUrl, String country) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        imageUrl = image;
    }
}

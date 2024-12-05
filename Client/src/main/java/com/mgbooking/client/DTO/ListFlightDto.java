package com.mgbooking.client.DTO;

public class ListFlightDto {
    private int id;
    private String name;
    private String image_url;
    private String nameCountry;

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setName(String name) {
        this.name = name;
    }


}

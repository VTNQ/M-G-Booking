package com.mgbooking.client.DTO;

import org.springframework.web.multipart.MultipartFile;

public class AirlineDTO {

    private String name;

    private int country_id;

    private MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }


    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id( int country_id) {
        this.country_id = country_id;
    }

    public  MultipartFile getImage() {
        return image;
    }

    public void setImage( MultipartFile image) {
        this.image = image;
    }
}

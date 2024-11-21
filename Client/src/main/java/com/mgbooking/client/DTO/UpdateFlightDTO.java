package com.mgbooking.client.DTO;

public class UpdateFlightDTO {
private int id;
private  String name;
private int idCountry;
private String image;

    public UpdateFlightDTO(int id, String name, int idCountry, String image) {
        this.id = id;
        this.name = name;
        this.idCountry = idCountry;
        this.image = image;
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

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

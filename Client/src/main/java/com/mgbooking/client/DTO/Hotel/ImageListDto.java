package com.mgbooking.client.DTO.Hotel;

public class ImageListDto {
    private int id;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    private String image;
    private int hotelId;
    public int getId() {
        return id;
    }

    public ImageListDto(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

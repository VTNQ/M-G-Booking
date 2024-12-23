package com.mgbooking.server.DTOS.Hotel;

public class ImageListDto {
    private int id;
    private String image;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    private int hotelId;
    public int getId() {
        return id;
    }

    public ImageListDto(int id, String image,int hotelId) {
        this.id = id;
        this.image = image;
        this.hotelId = hotelId;
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

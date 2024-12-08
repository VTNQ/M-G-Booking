package com.mgbooking.server.DTOS.Hotel;

public class ImageListDto {
    private int id;
    private String image;

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

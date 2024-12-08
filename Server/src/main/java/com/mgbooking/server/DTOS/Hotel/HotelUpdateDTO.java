package com.mgbooking.server.DTOS.Hotel;

import java.util.List;

public class HotelUpdateDTO {
    private int id;
    private String name;
    private String address;
    private Integer cityId;
    private String decription;

    public HotelUpdateDTO(int id, String name, String address, Integer cityId, String decription, Integer ownerId,int district_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cityId = cityId;
        this.decription = decription;
        this.ownerId = ownerId;
        this.district_id = district_id;
    }

    private Integer ownerId;
    private List<ImageListDto>imageList;

    public List<ImageListDto> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListDto> imageList) {
        this.imageList = imageList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    private int district_id;
}

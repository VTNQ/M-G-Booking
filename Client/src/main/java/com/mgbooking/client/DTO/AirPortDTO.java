package com.mgbooking.client.DTO;


public class AirPortDTO {
    private Integer id;

    private String name;

    private int city_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public  int getCity_id() {
        return city_id;
    }

    public void setCity_id( int city_id) {
        this.city_id = city_id;
    }
}

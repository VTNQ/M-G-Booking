package com.mgbooking.server.DTOS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class AirLineDTO {
    private Integer id;
    @NotNull(message = "Name is Required")
    private String name;
    @Min(value = 1,message = "Country is required")
    private int country_id;
    @NotNull(message = "Image is required")
    private MultipartFile image;

    public AirLineDTO(int id, String name, int country_id, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        this.image = image;
    }

    public @NotNull(message = "Name is Required") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name is Required") String name) {
        this.name = name;
    }

    @Min(value = 1, message = "Country is required")
    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(@Min(value = 1, message = "Country is required") int country_id) {
        this.country_id = country_id;
    }

    public @NotNull(message = "Image is required") MultipartFile getImage() {
        return image;
    }

    public void setImage(@NotNull(message = "Image is required") MultipartFile image) {
        this.image = image;
    }
}

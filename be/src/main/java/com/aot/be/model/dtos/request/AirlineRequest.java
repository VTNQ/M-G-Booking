package com.aot.be.model.dtos.request;

import lombok.*;

@Data
public class AirlineRequest {

    private String airlineName;

    private Integer countryId;

    private String website;

    private String hotline;

}

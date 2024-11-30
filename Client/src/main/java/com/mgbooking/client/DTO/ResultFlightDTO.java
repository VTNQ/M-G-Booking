package com.mgbooking.client.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ResultFlightDTO {
    private String imageUrl;
    private String nameCity;
    private Instant arrivalTime;
    private int id;
    private String nameAirline;
    private String timeDepart;
    private String dateDepart;
    private String durationString;
    private String nameAiport;

    public String getNameAiport() {
        return nameAiport;
    }

    public void setNameAiport(String nameAiport) {
        this.nameAiport = nameAiport;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    private String dateArrival;


    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    private String timeArrival;
    public String getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(String timeDepart) {
        this.timeDepart = timeDepart;
    }

    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd h:mm a")
            .withZone(ZoneId.of("UTC"));
    public String getFormattedArrivalTime() {
        return FORMATTER.format(arrivalTime);
    }

    public String getFormattedDepartureTime() {
        return FORMATTER.format(departureTime);
    }
    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Instant arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Instant getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Instant departureTime) {
        this.departureTime = departureTime;
    }

    private Instant departureTime;
    public ResultFlightDTO(int id,String imageUrl,String nameCity, Instant arrivalTime, Instant departureTime) {
        this.imageUrl = imageUrl;
        this.nameCity=nameCity;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getImageUrl() {
        return imageUrl;
    }



    public void setImageUrl(String imageUrl) {}

}

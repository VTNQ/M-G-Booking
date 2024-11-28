package com.mgbooking.server.DTOS;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightListDto {
    private int id;
    private String departure_time;
    private String arrival_time;
    private int airline_id;
    public int getId() {
        return id;
    }

    @JsonIgnore
    public Instant getDepartureInstant() {
        return convertToInstant(departure_time);
    }
    @JsonIgnore
    public Instant getArrivalInstant() {
        return convertToInstant(arrival_time);
    }

    private Instant convertToInstant(String timeString) {
        if (timeString == null || timeString.isEmpty()) {
            throw new IllegalArgumentException("Time string cannot be null or empty");
        }

        // Parse string to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(timeString, formatter);

        // Convert LocalDateTime to Instant (UTC)
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }



    private int departure_airport;
    private int arrival_airport;

    public int getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(int departure_airport) {
        this.departure_airport = departure_airport;
    }

    public int getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(int arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void setId(int id) {
        this.id = id;
    }
}

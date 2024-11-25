package com.mgbooking.server.DTOS;

import java.util.List;

public class FlightListDto {
    private int id;
    private String departure_time;
    private String arrival_time;
    private int airline_id;
    public int getId() {
        return id;
    }
    private List<DetailFlightDTO> detailFlights;

    public List<DetailFlightDTO> getDetailFlights() {
        return detailFlights;
    }

    public void setDetailFlights(List<DetailFlightDTO> detailFlights) {
        this.detailFlights = detailFlights;
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

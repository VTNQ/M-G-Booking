package com.mgbooking.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalTime;
import java.util.List;

public class FlightDTO {
    private Integer id;

    @JsonProperty("airline_id")
    private int airline_id;

    private int departure_airport;

    private int arrival_airport;

    private String departureTime;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id( int airline_id) {
        this.airline_id = airline_id;
    }

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

    public  String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(  String departureTime) {
        this.departureTime = departureTime;
    }

    public  String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime( String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private String arrivalTime;
   private List<DetailFlight>detailFlights;

    public List<DetailFlight> getDetailFlights() {
        return detailFlights;
    }

    public void setDetailFlights(List<DetailFlight> detailFlights) {
        this.detailFlights = detailFlights;
    }
}

package com.mgbooking.server.DTOS;

import java.time.LocalDate;

public class SearchFlightDTO {
    private int departureAirport;
    private int arrivalAirport;
    private LocalDate departureTime;

    public int getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(int arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public int getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(int departureAirport) {
        this.departureAirport = departureAirport;
    }
}

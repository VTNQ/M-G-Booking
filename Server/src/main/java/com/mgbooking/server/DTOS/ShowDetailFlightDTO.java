package com.mgbooking.server.DTOS;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ShowDetailFlightDTO {
    private String imageUrl;
    private String nameAirline;
    private String timeDepart;
    private String DateDepart;
    private String DepatureAirPorts;
    private String timeArrival;
    private String DateArrival;
    private String ArrivalAirPort;

    public String getArrivalAirPort() {
        return ArrivalAirPort;
    }

    public void setArrivalAirPort(String arrivalAirPort) {
        ArrivalAirPort = arrivalAirPort;
    }

    public String getDateArrival() {
        return DateArrival;
    }

    public void setDateArrival(String dateArrival) {
        DateArrival = dateArrival;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getDepatureAirPorts() {
        return DepatureAirPorts;
    }

    public void setDepatureAirPorts(String depatureAirPorts) {
        DepatureAirPorts = depatureAirPorts;
    }

    public String getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(String dateDepart) {
        DateDepart = dateDepart;
    }

    public ShowDetailFlightDTO(String imageUrl, String nameAirline, Instant departureTime,Instant DateDepart,String depatureAirPorts,Instant timeArrival,Instant DateArrival,String arrivalAirPort) {
        this.imageUrl = imageUrl;
        this.nameAirline = nameAirline;

        // Định dạng thời gian ở đây
        this.timeDepart = formatTime(departureTime);
        this.DateDepart=formatDate(DateDepart);
        this.DepatureAirPorts=depatureAirPorts;
        this.timeArrival=formatTime(timeArrival);
        this.DateArrival=formatDate(DateArrival);
        this.ArrivalAirPort=arrivalAirPort;
    }

    private String formatTime(Instant departureTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
                .withZone(ZoneId.of("UTC"));
        return formatter.format(departureTime);
    }
    private String formatDate(Instant departureTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yy")
                .withZone(ZoneId.of("UTC")); // Múi giờ UTC
        return formatter.format(departureTime);
    }
    // Getters và Setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }

    public String getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(String timeDepart) {
        this.timeDepart = timeDepart;
    }
}

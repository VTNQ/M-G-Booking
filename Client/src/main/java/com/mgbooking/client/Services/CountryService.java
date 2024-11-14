package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.Country;

import java.util.List;

public interface CountryService {
    public List<Country>GetCountry(String token);
    public boolean CreateCountry(String token,Country country);
    public Country FindCountry(String token,int id);
    public boolean UpdateCountry(String token,Country country);
    public boolean DeleteCountry(String token,int id);
}

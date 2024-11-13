package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.Country;

import java.util.List;

public interface CountryService {
    public List<Country>GetCountry();
    public boolean CreateCountry(String token,Country country);

}

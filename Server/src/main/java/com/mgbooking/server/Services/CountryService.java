package com.mgbooking.server.Services;

import com.mgbooking.server.Entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CountryService {
public List<Country>findAll();
public Page<Country> findByPage(Pageable pageable,String name);
public boolean CreateCountry(Country country);
public boolean DeleteCountry(int id);
public Country findById(int id);

}

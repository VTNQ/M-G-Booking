package com.mgbooking.server.Services;

import org.springframework.validation.BindingResult;

import java.util.List;

public interface ValidationService {
    public List<String>validate(Object dto, BindingResult bindingResult);
}

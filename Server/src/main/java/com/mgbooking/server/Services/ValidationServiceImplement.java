package com.mgbooking.server.Services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ValidationServiceImplement implements ValidationService{
    @Override
    public List<String> validate(Object dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        }
        return null;
    }
}

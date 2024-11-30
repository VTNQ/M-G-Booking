package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;

public interface UserService {
public boolean RegisterUser(RegisterUser registerUser);
public Object UpdateAccount(String token, AccountDto accountDto);
}

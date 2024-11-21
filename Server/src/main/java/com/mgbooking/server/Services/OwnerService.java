package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.RegisterAccountDto;

public interface OwnerService {
    public boolean registerAccount(RegisterAccountDto registerAccountDto);
    public AccountDto GetAccount(String token);
}

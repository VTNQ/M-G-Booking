package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOs.RegisterAccountDto;
import com.mgbooking.server.Entities.Account;

public interface AccountService {
    public boolean registerAccount(RegisterAccountDto registerAccountDto);
    public AccountDto GetAccount(String token);
}

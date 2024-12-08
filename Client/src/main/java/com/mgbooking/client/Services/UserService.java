package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.Account.AccountAdmin;
import com.mgbooking.client.DTO.Account.RegisterAdmin;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;

import java.util.List;

public interface UserService {
public boolean RegisterUser(RegisterUser registerUser);
public Object UpdateAccount(String token, AccountDto accountDto);
public Object RegisterAdmin(String token, RegisterAdmin registerAdmin);
public List<AccountAdmin>GetAccountAdmin(String token);

}

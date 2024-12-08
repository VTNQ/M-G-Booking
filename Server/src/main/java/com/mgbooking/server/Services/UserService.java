package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.Account.AccountAdmin;
import com.mgbooking.server.DTOS.Account.RegisterAdmin;
import com.mgbooking.server.DTOS.RegisterUser;

import java.util.List;

public interface UserService {
    public boolean RegisterUser(RegisterUser user);
    public boolean ForgetAccount(String email);
    public boolean CheckOTP(String Email,String otp);
    public boolean ChangePassword(String Email,String Password);
    public boolean ResendOTP(String Email);
    public boolean RegisterAdmin(RegisterAdmin admin);
    public List<AccountAdmin>getAccountAdmin();
}

package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.RegisterUser;

public interface UserService {
    public boolean CreateUser(RegisterUser user);
}
package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.RegisterOwnerDto;

public interface OwnerService {
    public boolean RegisterOwner(RegisterOwnerDto registerOwnerDto);
}

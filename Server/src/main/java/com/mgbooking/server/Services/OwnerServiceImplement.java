package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.RegisterOwnerDto;
import com.mgbooking.server.Entities.Owner;
import com.mgbooking.server.Repositories.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImplement implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public boolean RegisterOwner(RegisterOwnerDto registerOwnerDto) {
        try{
            if (!registerOwnerDto.getPassword().equals(registerOwnerDto.getConfirmPassword())) {

                return false;
            }

            Owner owner =modelMapper.map(registerOwnerDto, Owner.class);
            owner.setPassword(BCrypt.hashpw(registerOwnerDto.getConfirmPassword(), BCrypt.gensalt()));
            ownerRepository.save(owner);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

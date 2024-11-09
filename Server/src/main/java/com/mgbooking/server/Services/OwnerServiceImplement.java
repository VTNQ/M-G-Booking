package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.RegisterOwnerDto;
import com.mgbooking.server.Repositories.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImplement implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;

    private  void SendConfirmationEmail(String Email,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Email);
        message.setSubject("Account Registration Confirmation");
        message.setText(text);

        mailSender.send(message);
    }
    @Override
    public boolean RegisterOwner(RegisterOwnerDto registerOwnerDto) {
        try{
            if (!registerOwnerDto.getPassword().equals(registerOwnerDto.getConfirmPassword())) {

                return false;
            }

            Owner owner =modelMapper.map(registerOwnerDto, Owner.class);
            owner.setPassword(BCrypt.hashpw(registerOwnerDto.getConfirmPassword(), BCrypt.gensalt()));
            ownerRepository.save(owner);
            SendConfirmationEmail(registerOwnerDto.getEmail(),"Thank you for registering with MGBooking. Your account has been successfully created.\n Password:"+registerOwnerDto.getConfirmPassword());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

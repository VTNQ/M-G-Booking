package com.mgbooking.server.Services;

import com.mgbooking.server.DTOs.AccountDto;
import com.mgbooking.server.DTOs.RegisterAccountDto;
import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Repositories.AccountRepository;
import org.hibernate.annotations.SecondaryRow;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement implements AccountService {

    private AccountRepository accountRepository;
    private ModelMapper modelMapper;
    private JavaMailSenderImpl mailSender;

    private  void SendConfirmationEmail(String Email,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Email);
        message.setSubject("Account Registration Confirmation");
        message.setText(text);

        mailSender.send(message);
    }

    @Override
    public boolean registerAccount(RegisterAccountDto registerAccountDto) {
//        try{
//            if (!accountDto.getPassword().equals(accountDto.getConfirmPassword())) {
//
//                return false;
//            }
//
//            Account owner =modelMapper.map(registerOwnerDto, Account.class);
//            owner.setPassword(BCrypt.hashpw(registerOwnerDto.getConfirmPassword(), BCrypt.gensalt()));
//            accountRepository.save(owner);
//            SendConfirmationEmail(registerOwnerDto.getEmail(),"Thank you for registering with MGBooking. Your account has been successfully created.\n Password:"+registerOwnerDto.getConfirmPassword());
//            return true;
//        }catch (Exception e){
//            return false;
//        }
        return false;
    }
}

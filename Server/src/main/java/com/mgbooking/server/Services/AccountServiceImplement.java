package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOs.RegisterAccountDto;
import com.mgbooking.server.Repositories.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
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

    @Override
    public AccountDto GetAccount(String token) {
        try {
            String secretKey="sRbgDVJHhto1l0DxFi09N/5phc9FEEWfN4MQIzWKBEs=";
            if(token.startsWith("Bearer ")){
                token = token.substring(7);
            }
            Claims claims= Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String username=claims.getSubject();
           return modelMapper.map(accountRepository.findByEmail(username),new TypeToken<AccountDto>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

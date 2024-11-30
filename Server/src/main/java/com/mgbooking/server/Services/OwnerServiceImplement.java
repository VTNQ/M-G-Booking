package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.RegisterAccountDto;
import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Entities.Level;
import com.mgbooking.server.Repositories.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OwnerServiceImplement implements OwnerService {
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
        try{
            if (!registerAccountDto.getPassword().equals(registerAccountDto.getConfirmPassword())) {

                return false;
            }

            Account owner =modelMapper.map(registerAccountDto, Account.class);
            owner.setPassword(BCrypt.hashpw(registerAccountDto.getConfirmPassword(), BCrypt.gensalt()));
            owner.setAccountType("ROLE_OWNER");
            Level level=new Level();
            level.setId(1);
            owner.setLevel(level);
            owner.setUsername(GeneateUsername(owner.getEmail()));
            accountRepository.save(owner);
            SendConfirmationEmail(registerAccountDto.getEmail(),"Thank you for registering with MGBooking. Your account has been successfully created.\n Password:"+registerAccountDto.getConfirmPassword());
            return true;
        }catch (Exception e){
            return false;
        }

    }
    private String GeneateUsername(String Email ){
        if(Email!=null && Email.endsWith("@gmail.com")){
            String baseName=Email.substring(0,Email.indexOf("@"));
            int randomNumber=new Random().nextInt(1000);
            return baseName+randomNumber;
        }
        return null;
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

    @Override
    public boolean updateAccount(AccountDto accountDto) {
        try {
           Account owner=modelMapper.map(accountDto,Account.class);
            owner.setPassword(BCrypt.hashpw(accountDto.getPassword(), BCrypt.gensalt()));
            owner.setAccountType(accountDto.getAccountType());
            Level level=new Level();
            level.setId(1);
            owner.setLevel(level);
            accountRepository.save(owner);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

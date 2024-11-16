package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Entities.Level;
import com.mgbooking.server.Repositories.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private AccountRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;

    private  void SendConfirmationEmail(String Email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Email);
        message.setSubject("Account Registration Confirmation");
        message.setText("Thank you for registering with MGBooking. Your account has been successfully created.");

        mailSender.send(message);
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
    public boolean CreateUser(RegisterUser user) {
        try{
            Account account =modelMapper.map(user, Account.class);

            account.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            account.setUsername(GeneateUsername(user.getEmail()));
            Level level=new Level();
            level.setId(1);
            account.setLevel(level);
            userRepository.save(account);
            SendConfirmationEmail(user.getEmail());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

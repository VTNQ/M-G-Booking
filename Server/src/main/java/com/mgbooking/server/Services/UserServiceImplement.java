package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.Account.AccountAdmin;
import com.mgbooking.server.DTOS.Account.RegisterAdmin;
import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Entities.Level;
import com.mgbooking.server.Repositories.AccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
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
    private void SendOTPEmail(String Email,String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(Email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp + "\n\nThis code is valid for 10 minutes.");
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
    public boolean RegisterUser(RegisterUser user) {
        try{
            Account account =modelMapper.map(user, Account.class);

            account.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            account.setUsername(GeneateUsername(user.getEmail()));
            Level level=new Level();
            level.setId(1);
            account.setLevel(level);
            account.setCityId(1);
            userRepository.save(account);
            SendConfirmationEmail(user.getEmail());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private String GenerateOTP(){
        Random random=new Random();
        int otp=1000+random.nextInt(1000);
        return String.valueOf(otp);
    }
    @Override
    public boolean ForgetAccount(String email) {
        try {
            Account account = userRepository.findByEmail(email);
            if(account==null){
                return false;
            }
            String otp=GenerateOTP();
            Instant CurrentInstant=Instant.now();
            account.setOtp(otp);
            account.setCreatedOTP(CurrentInstant);
            userRepository.save(account);
            SendOTPEmail(email,otp);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean CheckOTP(String Email, String otp) {
        try {
        Account account=userRepository.checkOTP(Email,otp);
        if(account==null ){
            return false;
        }
            Instant otpCreatedTime = account.getCreatedOTP();
            Instant currentTime = Instant.now();
            Duration duration = Duration.between(otpCreatedTime, currentTime);
            if(duration.toMillis() > 120000){
                return false;
            }
            account.setOtp(null);
        account.setCreatedOTP(null);
        userRepository.save(account);
        return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ChangePassword(String Email, String Password) {
        try {
            Account account=userRepository.findByEmail(Email);
            if(account==null){
                return false;
            }

            account.setPassword(BCrypt.hashpw(Password, BCrypt.gensalt()));
            userRepository.save(account);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ResendOTP(String Email) {
        try {
        Account account=userRepository.findByEmail(Email);
        if(account==null){
            return false;
        }
            Instant otpCreatedTime = account.getCreatedOTP();
            Instant currentTime = Instant.now();
            Duration duration = Duration.between(otpCreatedTime, currentTime);
            if(duration.toMillis() < 120000){
                return false;
            }
            String otp=GenerateOTP();
            Instant CurrentInstant=Instant.now();
            account.setOtp(otp);
            account.setCreatedOTP(CurrentInstant);
            userRepository.save(account);
            SendOTPEmail(Email,otp);
            return true;
        }catch (Exception ex){
        ex.printStackTrace();
        return false;
        }
    }

    @Override
    public boolean RegisterAdmin(RegisterAdmin admin) {
        try {
            Account account=modelMapper.map(admin, Account.class);
            account.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            account.setAccountType("ROLE_ADMIN");
            account.setUsername(GeneateUsername(admin.getEmail()));
            Level level=new Level();
            level.setId(1);
            account.setLevel(level);
            userRepository.save(account);
            SendConfirmationEmail(admin.getEmail());
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AccountAdmin> getAccountAdmin() {
    return userRepository.GetAccountAdmin();
    }
}

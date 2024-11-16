package com.mgbooking.server.Services;

import com.mgbooking.server.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceDetail implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(Email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + account);
        }
        String roleName = account.getAccountType();

        return new User(account.getEmail(), account.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(roleName)));
    }


}

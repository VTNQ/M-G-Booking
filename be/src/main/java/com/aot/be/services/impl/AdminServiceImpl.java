package com.aot.be.services.impl;

import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.entities.AdminAccount;
import com.aot.be.repositories.AdminAccountRepository;
import com.aot.be.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminAccountRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminAccount admin = repository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return UserPrincipal.createAdmin(admin,admin.getAccountType());
    }
}

package com.mgbooking.server.Services;

import
import com.mgbooking.server.Entities.Admin;
import com.mgbooking.server.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new User(
                admin.getUsername(),
                admin.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(admin.getType())
        );
    }
}

package com.mgbooking.server.Services;

import com.mgbooking.server.Entities.Owner;
import com.mgbooking.server.Repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceDetail implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner=ownerRepository.findOwnerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Owner Not Found"));
        return new User(
                owner.getEmail(),
                owner.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_OWNER")
        );
    }
}

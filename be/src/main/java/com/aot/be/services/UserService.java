package com.aot.be.services;

import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.entities.Role;
import com.aot.be.model.entities.User;
import com.aot.be.repositories.RoleRepository;
import com.aot.be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService extends
        BaseService<User, Integer>,
        UserDetailsService {
    boolean existsByEmail(String email);

    User save(User user);

    String resolveRoleName(Integer roleId);

    boolean existsByPhone(String phone);
    UserDetails loadUserById(Integer id);
}


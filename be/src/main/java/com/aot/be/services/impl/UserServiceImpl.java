package com.aot.be.services.impl;

import com.aot.be.configurations.UserPrincipal;
import com.aot.be.model.entities.Role;
import com.aot.be.model.entities.User;
import com.aot.be.repositories.RoleRepository;
import com.aot.be.repositories.UserRepository;
import com.aot.be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserPrincipal.create(user,user.getAccountType().toString());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public UserDetails loadUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserPrincipal.create(user,user.getAccountType().toString());
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public String resolveRoleName(Integer roleId) {
        if (roleId == null) {
            return "USER";
        }
        return roleRepository.findById(roleId)
                .map(Role::getName)
                .orElse("USER");
    }

    @Override
    @Transactional
    public void create(User entity) {
         userRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(Integer id, User entity) {
        entity.setId(id);
         userRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void softDelete(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setIsActive(false);
            userRepository.save(user);
        });
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }


    @Override
    public Page<User> getAll(
            Integer page, Integer size, String sort, String filter, String searchField,
            String searchValue, Boolean all
                            ) {
        return null;
    }


    @Override
    @Transactional
    public List<User> createAll(List<User> entities) {
        return userRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Integer> ids) {
        userRepository.deleteAllById(ids);
    }
}

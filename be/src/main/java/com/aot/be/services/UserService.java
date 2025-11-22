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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements BaseService<User, Integer>, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        String roleName = resolveRoleName(user.getAccountType());
        return UserPrincipal.create(user, roleName);
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
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public User update(Integer id, User entity) {
        entity.setId(id);
        return userRepository.save(entity);
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
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


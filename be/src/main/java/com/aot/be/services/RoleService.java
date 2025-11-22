package com.aot.be.services;

import com.aot.be.model.entities.Role;
import com.aot.be.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService implements BaseService<Role, Integer> {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Role create(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    @Transactional
    public Role update(Integer id, Role entity) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
        existing.setName(entity.getName());
        existing.setDescription(entity.getDescription());
        existing.setPermissions(entity.getPermissions());
        return roleRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void softDelete(Integer id) {
        throw new UnsupportedOperationException("Soft delete is not supported for Role");
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public boolean existsById(Integer id) {
        return roleRepository.existsById(id);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    @Transactional
    public List<Role> createAll(List<Role> entities) {
        return roleRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Integer> ids) {
        roleRepository.deleteAllById(ids);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}

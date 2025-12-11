package com.aot.be.services.impl;

import com.aot.be.model.entities.Role;
import com.aot.be.repositories.RoleRepository;
import com.aot.be.services.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void create(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(Integer id, Role entity) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
        existing.setName(entity.getName());
        existing.setDescription(entity.getDescription());
        existing.setPermissions(entity.getPermissions());
         roleRepository.save(existing);
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
    public Page<Role> getAll(
            Integer page, Integer size, String sort, String filter, String searchField,
            String searchValue, Boolean all
                            ) {
        return null;
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

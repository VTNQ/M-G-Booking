package com.aot.be.services.impl;

import com.aot.be.services.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {
    @Override
    public void create(T entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    @Override
    public void update(ID id, T entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(ID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void softDelete(ID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<T> findById(ID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Page<T> getAll(Integer page, Integer size, String sort, String filter,
                          String searchField, String searchValue, Boolean all) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<T> createAll(List<T> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllByIds(List<ID> ids) {
        throw new UnsupportedOperationException("Not implemented");
    }

}

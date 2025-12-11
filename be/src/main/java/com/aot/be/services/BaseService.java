package com.aot.be.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Interface service chung cho các CRUD operations cơ bản
 * @param <T> Entity type
 * @param <ID> ID type (thường là Long, String, UUID, etc.)
 */
public interface BaseService<T, ID> {

    /**
     * Tạo mới một entity
     * @param entity Entity cần tạo
     */
    void create(T entity);

    /**
     * Cập nhật một entity
     * @param id ID của entity cần cập nhật
     * @param entity Entity chứa dữ liệu mới
     */
    void update(ID id, T entity);

    /**
     * Xóa một entity theo ID
     * @param id ID của entity cần xóa
     */
    void delete(ID id);

    /**
     * Xóa mềm (soft delete) - đánh dấu là đã xóa
     * @param id ID của entity cần xóa mềm
     */
    void softDelete(ID id);

    /**
     * Tìm entity theo ID
     * @param id ID của entity
     * @return Optional chứa entity nếu tìm thấy
     */
    Optional<T> findById(ID id);



    Page<T> getAll(Integer page,Integer size,String sort,String filter,
                   String searchField,String searchValue,Boolean all);


    /**
     * Tạo nhiều entities cùng lúc
     * @param entities List các entities cần tạo
     * @return List các entities đã được tạo
     */
    List<T> createAll(List<T> entities);

    /**
     * Xóa nhiều entities theo IDs
     * @param ids List các IDs cần xóa
     */
    void deleteAllByIds(List<ID> ids);
}
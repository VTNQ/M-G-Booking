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
     * @return Entity đã được tạo
     */
    T create(T entity);

    /**
     * Cập nhật một entity
     * @param id ID của entity cần cập nhật
     * @param entity Entity chứa dữ liệu mới
     * @return Entity đã được cập nhật
     */
    T update(ID id, T entity);

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

    /**
     * Lấy tất cả entities
     * @return List các entities
     */
    List<T> findAll();

    /**
     * Lấy tất cả entities với phân trang
     * @param pageable Thông tin phân trang
     * @return Page chứa các entities
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Kiểm tra entity có tồn tại không
     * @param id ID của entity
     * @return true nếu tồn tại, false nếu không
     */
    boolean existsById(ID id);

    /**
     * Đếm tổng số entities
     * @return Tổng số entities
     */
    long count();

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
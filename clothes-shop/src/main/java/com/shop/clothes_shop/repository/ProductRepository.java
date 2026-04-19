package com.shop.clothes_shop.repository;

import com.shop.clothes_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Hàm tìm kiếm theo tên có chứa từ khóa (không phân biệt hoa thường)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
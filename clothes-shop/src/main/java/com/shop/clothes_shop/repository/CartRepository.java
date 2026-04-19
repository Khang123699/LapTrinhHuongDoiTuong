package com.shop.clothes_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.clothes_shop.model.Cart;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    List<Cart> findByUserId(Integer userId);
    
    Optional<Cart> findByUserIdAndInventoryId(Integer userId, Integer inventoryId);
}
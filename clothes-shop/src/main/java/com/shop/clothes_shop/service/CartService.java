package com.shop.clothes_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.clothes_shop.model.Cart;
import com.shop.clothes_shop.model.Product;
import com.shop.clothes_shop.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartByUser(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addToCart(Integer userId, Integer inventoryId, int quantity, double price) {
        Optional<Cart> existingCart = cartRepository.findByUserIdAndInventoryId(userId, inventoryId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            Cart newCart = new Cart(userId, inventoryId, quantity, price);
            cartRepository.save(newCart);
        }
    }

    public void updateQuantity(Integer cartId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            if (quantity <= 0) {
                cartRepository.delete(cart);
            } else {
                cart.setQuantity(quantity);
                cartRepository.save(cart);
            }
        }
    }

    public void removeProduct(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    public double calculateTotalAmount(Integer userId) {
        List<Cart> cartList = cartRepository.findByUserId(userId);
        double total = 0;
        
        for (Cart item : cartList) {
            Product product = item.getInventory().getProduct();
            
            // KIỂM TRA LOGIC GIẢM GIÁ:
            // Nếu sản phẩm có giá giảm và giá giảm nhỏ hơn giá gốc -> Lấy giá giảm
            // Ngược lại -> Lấy giá gốc
            double currentPrice = product.getPrice(); // Mặc định là giá gốc
            
            if (product.getDiscountPrice() != null && product.getDiscountPrice() < product.getPrice()) {
                currentPrice = product.getDiscountPrice();
            }
            
            // Cộng dồn vào tổng tiền: (Giá hiện tại * Số lượng)
            total += currentPrice * item.getQuantity();
        }
        
        return total;
    }
}
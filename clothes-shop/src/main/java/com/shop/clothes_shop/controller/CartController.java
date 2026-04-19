package com.shop.clothes_shop.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shop.clothes_shop.model.Cart;
import com.shop.clothes_shop.service.CartService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Giả lập User ID cho tuần 2 (Vì bạn Đạt chưa làm xong phần Đăng nhập)
    private final Integer MOCK_USER_ID = 1;

    @GetMapping
    public String showCart(Model model) {
        List<Cart> cartList = cartService.getCartByUser(MOCK_USER_ID);
        double totalAmount = cartService.calculateTotalAmount(MOCK_USER_ID);
        
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalAmount", totalAmount);
        return "cart"; 
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer inventoryId, @RequestParam int quantity, @RequestParam double price) {
        cartService.addToCart(MOCK_USER_ID, inventoryId, quantity, price);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Integer cartId, @RequestParam int quantity) {
        cartService.updateQuantity(cartId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Integer id) {
        cartService.removeProduct(id);
        return "redirect:/cart";
    }
    @PostMapping("/add-ajax")
    @ResponseBody
    public String addToCartAjax(@RequestParam Integer inventoryId, @RequestParam int quantity, @RequestParam double price) {
        cartService.addToCart(MOCK_USER_ID, inventoryId, quantity, price);
        return "OK"; // Chỉ trả về phản hồi thành công, không chuyển hướng
    }
 // Thêm phương thức này vào CartController.java
    @GetMapping("/count")
    @ResponseBody
    public Long getCartCount() {
        List<Cart> cartList = cartService.getCartByUser(MOCK_USER_ID);
        // Tính tổng số lượng (quantity) của tất cả các dòng trong giỏ hàng
        return cartList.stream().mapToLong(Cart::getQuantity).sum();
    }
}
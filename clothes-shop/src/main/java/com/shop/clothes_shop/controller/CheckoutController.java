package com.shop.clothes_shop.controller;

import com.shop.clothes_shop.model.Cart;
import com.shop.clothes_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;
    private final Integer MOCK_USER_ID = 1;

    // Hiển thị trang Thanh Toán
    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        List<Cart> cartList = cartService.getCartByUser(MOCK_USER_ID);
        
        // Nếu giỏ hàng trống thì quay về trang sản phẩm
        if (cartList.isEmpty()) {
            return "redirect:/products";
        }
        
        double totalAmount = cartService.calculateTotalAmount(MOCK_USER_ID);
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }

    // Xử lý khi bấm nút "Hoàn tất đặt hàng"
    @PostMapping("/checkout/process")
    public String processCheckout(@RequestParam(required = false) String paymentMethod) {
        // GIẢ LẬP: Khách hàng đặt xong -> Xóa sạch các món trong giỏ hàng
        List<Cart> cartList = cartService.getCartByUser(MOCK_USER_ID);
        for (Cart cart : cartList) {
            cartService.removeProduct(cart.getId());
        }
        
        // Chuyển sang trang theo dõi đơn hàng
        return "redirect:/order-tracking";
    }

    // Hiển thị trang Hành trình kiện hàng (Demo)
    @GetMapping("/order-tracking")
    public String showOrderTracking() {
        return "order-tracking";
    }
 // Thêm đoạn này để tự động chuyển hướng trang chủ về trang sản phẩm
    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/products";
    }
}
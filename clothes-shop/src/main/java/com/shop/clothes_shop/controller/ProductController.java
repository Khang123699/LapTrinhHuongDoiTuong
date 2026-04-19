package com.shop.clothes_shop.controller;

import com.shop.clothes_shop.model.Product;
import com.shop.clothes_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. Hiển thị danh sách & Tìm kiếm
    @GetMapping
    public String listProducts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword); // Giữ lại từ khóa trên thanh search
        return "product-list"; 
    }

    // 2. Xem chi tiết sản phẩm (Chọn size, màu, xem tồn kho)
    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }
}
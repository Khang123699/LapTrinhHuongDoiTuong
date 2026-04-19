package com.shop.clothes_shop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "inventory_id", insertable = false, updatable = false)
    private Inventory inventory;

    public Inventory getInventory() { return inventory; }

    public Cart() {}

    public Cart(Integer userId, Integer inventoryId, int quantity, double price) {
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getInventoryId() { return inventoryId; }
    public void setInventoryId(Integer inventoryId) { this.inventoryId = inventoryId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

 // Tính tổng tiền cho 1 dòng sản phẩm trong giỏ hàng
    public double getTotalPrice() {
        Product product = this.inventory.getProduct();
        double currentPrice = product.getPrice();
        
        if (product.getDiscountPrice() != null && product.getDiscountPrice() < product.getPrice()) {
            currentPrice = product.getDiscountPrice();
        }
        
        return this.quantity * currentPrice;
    }
}

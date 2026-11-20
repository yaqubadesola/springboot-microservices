package com.relationship.jpa.lab5;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "lab5_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    // Price at the moment of purchase (a snapshot, NOT a live link to product.price)
    private BigDecimal unitPrice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private CustomerOrder order;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
    protected OrderItem() { }
    public OrderItem(CustomerOrder order, Product product, int quantity, BigDecimal unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public Long getId() { return id; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public CustomerOrder getOrder() { return order; }
    public Product getProduct() { return product; }
}
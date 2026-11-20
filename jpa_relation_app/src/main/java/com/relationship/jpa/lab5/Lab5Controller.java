package com.relationship.jpa.lab5;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/lab5")
public class Lab5Controller {
    public record CreateProduct(String name, BigDecimal price) { }
    public record ProductDto(Long id, String name, BigDecimal price) { }
    public record ChangePrice(BigDecimal price) { }
    public record OrderLine(Long productId, int quantity) { }
    public record CreateOrder(String customerName, List<OrderLine> items) { }
    public record OrderItemDto(String product, int quantity, BigDecimal unitPrice) { }
    public record OrderDto(Long id, String customerName,
                           List<OrderItemDto> items, BigDecimal total) { }
    private final Lab5Service service;
    Lab5Controller(Lab5Service service) {
        this.service = service;
    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody CreateProduct request) {
        return service.createProduct(request);
    }
    @GetMapping("/products")
    public List<ProductDto> products() {
        return service.listProducts();
    }
    @PutMapping("/products/{id}/price")
    public ProductDto changePrice(@PathVariable Long id, @RequestBody ChangePrice request) {
        return service.changePrice(id, request);
    }
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeOrder(@RequestBody CreateOrder request) {
        return service.placeOrder(request);
    }
    @GetMapping("/orders/{id}")
    public OrderDto order(@PathVariable Long id) {
        return service.order(id);
    }
}
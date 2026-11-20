package com.relationship.jpa.lab5;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@Service
class Lab5Service {
    private final ProductRepository products;
    private final CustomerOrderRepository orders;
    Lab5Service(ProductRepository products, CustomerOrderRepository orders) {
        this.products = products;
        this.orders = orders;
    }
    @Transactional
    public Lab5Controller.ProductDto createProduct(Lab5Controller.CreateProduct request) {
        return toDto(products.save(new Product(request.name(), request.price())));
    }
    @Transactional(readOnly = true)
    public List<Lab5Controller.ProductDto> listProducts() {
        return products.findAll().stream().map(this::toDto).toList();
    }
    @Transactional
    public Lab5Controller.ProductDto changePrice(Long productId, Lab5Controller.ChangePrice req) {
        Product product = product(productId);
        product.setPrice(req.price());
// dirty checking issues the UPDATE
        return toDto(product);
    }
    @Transactional
    public Lab5Controller.OrderDto placeOrder(Lab5Controller.CreateOrder request) {
        CustomerOrder order = new CustomerOrder(request.customerName());
        List<Lab5Controller.OrderLine> lines =
                request.items() == null ? List.of() : request.items();
        for (Lab5Controller.OrderLine line : lines) {
            order.addItem(product(line.productId()), line.quantity());
        }
        return toDto(orders.save(order));
    }
    @Transactional(readOnly = true)
    public Lab5Controller.OrderDto order(Long id) {
        return toDto(orders.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Order " + id + " not found")));
    }
    private Product product(Long id) {
        return products.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product " + id + " not found"));
    }
    private Lab5Controller.ProductDto toDto(Product p) {
        return new Lab5Controller.ProductDto(p.getId(), p.getName(), p.getPrice());
    }
    private Lab5Controller.OrderDto toDto(CustomerOrder o) {
        List<Lab5Controller.OrderItemDto> items = o.getItems().stream()
                .map(i -> new Lab5Controller.OrderItemDto(
                        i.getProduct().getName(), i.getQuantity(), i.getUnitPrice()))
                .toList();
        return new Lab5Controller.OrderDto(o.getId(), o.getCustomerName(), items, o.getTotal());
    }
}
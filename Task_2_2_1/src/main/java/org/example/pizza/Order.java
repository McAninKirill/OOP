package org.example.pizza;

public class Order {
    private final int orderNumber;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}

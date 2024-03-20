package org.example.pizza;

import java.util.concurrent.TimeUnit;

public class Baker extends Thread {
    private final int bakerId;
    private final int speed;
    private final BlockingQueue<Order> orderQueue;
    private final BlockingQueue<Pizza> warehouse;

    public Baker(int bakerId, int speed, BlockingQueue<Order> orderQueue, BlockingQueue<Pizza> warehouse) {
        this.bakerId = bakerId + 1;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = orderQueue.take();
                System.out.println("Baker " + bakerId + " took order " + order.getOrderNumber());
                TimeUnit.SECONDS.sleep(speed);
                Pizza pizza = new Pizza(order.getOrderNumber());
                System.out.println("Baker " + bakerId + " cooked pizza " + pizza.orderNumber());
                if (warehouse.remainingCapacity() > 0) {
                    warehouse.put(pizza);
                    System.out.println("Baker " + bakerId + " put pizza " + pizza.orderNumber() + " in the warehouse");
                } else {
                    System.out.println("Warehouse is full, Baker " + bakerId + " waits for a place in the warehouse");
                    warehouse.put(pizza);
                    System.out.println("Baker " + bakerId + " put pizza " + pizza.orderNumber() + " in the warehouse");
                }
            }
        } catch (InterruptedException ignored) {
        }
    }
}
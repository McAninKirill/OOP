package org.example.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Courier extends Thread {
    private final int courierId;
    private final int speed;
    private final BlockingQueue<Pizza> warehouse;

    public Courier(int courierId, int speed, BlockingQueue<Pizza> warehouse) {
        this.courierId = courierId + 1;
        this.speed = speed;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Pizza> pizzas = new ArrayList<>();
                while (!warehouse.isEmpty()) {
                    pizzas.add(warehouse.take());
                    System.out.println("Courier " + courierId + " took pizza " + pizzas.get(0).orderNumber());
                    TimeUnit.SECONDS.sleep(speed);
                    System.out.println("Courier " + courierId + " delivered pizza " + pizzas.get(0).orderNumber());
                    pizzas.remove(0);
                }
            }
        } catch (InterruptedException ignored) {
        }
    }
}

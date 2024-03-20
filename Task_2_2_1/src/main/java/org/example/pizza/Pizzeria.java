package org.example.pizza;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pizzeria {
    private final int nBakers;
    private final int mCouriers;
    private final int warehouseCapacity;
    private final int[] bakerSpeeds;
    private final int[] courierSpeed;
    private final BlockingQueue<Order> orderQueue;
    private final BlockingQueue<Pizza> warehouse;
    private final AtomicInteger orderNumber;
    private final int timeOfWork;
    private List<Thread> bakersThreads;
    private List<Thread> couriersThreads;

    public Pizzeria(int nBakers, int mCouriers, int warehouseCapacity, int[] bakerSpeeds, int[] courierSpeed, int timeOfWork) {
        this.nBakers = nBakers;
        this.mCouriers = mCouriers;
        this.warehouseCapacity = warehouseCapacity;
        this.bakerSpeeds = bakerSpeeds;
        this.courierSpeed = courierSpeed;
        this.orderQueue = new BlockingQueue<>(nBakers);
        this.warehouse = new BlockingQueue<>(warehouseCapacity);
        this.orderNumber = new AtomicInteger(0);
        this.timeOfWork = timeOfWork;
    }

    public void start() throws InterruptedException {
        this.bakersThreads = new ArrayList<>();
        this.couriersThreads = new ArrayList<>();
        for (int i = 0; i < nBakers; i++) {
            Baker t = new Baker(i, bakerSpeeds[i], orderQueue, warehouse);
            t.start();
            bakersThreads.add(t);
        }
        for (int i = 0; i < mCouriers; i++) {
            Courier t = new Courier(i, courierSpeed[i], warehouse);
            t.start();
            couriersThreads.add(t);
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeOfWork) {
            Order order = new Order(orderNumber.incrementAndGet());
            System.out.println("Order " + order.getOrderNumber() + " arrived");
            orderQueue.put(order);
            TimeUnit.SECONDS.sleep(5);
        }
        if (orderQueue.isEmpty() && warehouse.isEmpty()) {
            System.out.println("All orders are completed");
        } else {
            System.out.println("Sorry, but we're closed.");
        }
        bakersThreads.forEach(Thread::interrupt);
        couriersThreads.forEach(Thread::interrupt);
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream fileInputStream = ClassLoader.getSystemResourceAsStream("pizzeria.json")) {
            PizzeriaJson pizzeriaJson = objectMapper.readValue(fileInputStream, PizzeriaJson.class);
            int nBakers = pizzeriaJson.nBakers();
            int mCouriers = pizzeriaJson.mCouriers();
            int timeOfWork = pizzeriaJson.timeOfWork();
            int warehouseCapacity = pizzeriaJson.warehouseCapacity();
            int[] bakerSpeeds = pizzeriaJson.bakerSpeeds();
            int[] courierSpeed = pizzeriaJson.courierSpeed();
            Pizzeria pizzeria = new Pizzeria(nBakers, mCouriers, warehouseCapacity, bakerSpeeds, courierSpeed, timeOfWork);
            pizzeria.start();
        }
    }
}

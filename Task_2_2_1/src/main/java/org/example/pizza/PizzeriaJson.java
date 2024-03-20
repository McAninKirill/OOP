package org.example.pizza;

public record PizzeriaJson(int nBakers, int mCouriers, int warehouseCapacity, int[] bakerSpeeds, int[] courierSpeed,
                           int timeOfWork) {
}

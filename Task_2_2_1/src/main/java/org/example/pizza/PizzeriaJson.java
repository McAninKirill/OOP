package org.example.pizza;

import java.util.Arrays;
import java.util.Objects;

public class PizzeriaJson{
    private final int nBakers;
    private final int mCouriers;
    private final int warehouseCapacity;
    private final int[] bakerSpeeds;
    private final int[] courierSpeed;
    private final int timeOfWork;


    public PizzeriaJson(int nBakers, int mCouriers, int warehouseCapacity,
                        int[] bakerSpeeds, int[] courierSpeed, int timeOfWork){
        this.nBakers = nBakers;
        this.mCouriers = mCouriers;
        this.warehouseCapacity = warehouseCapacity;
        this.bakerSpeeds = bakerSpeeds;
        this.courierSpeed = courierSpeed;
        this.timeOfWork = timeOfWork;
    }

    public int nBakers() {
        return nBakers;
    }

    public int mCouriers() {
        return mCouriers;
    }

    public int timeOfWork() {
        return timeOfWork;
    }

    public int warehouseCapacity() {
        return warehouseCapacity;
    }

    public int[] bakerSpeeds() {
        return bakerSpeeds;
    }

    public int[] courierSpeed() {
        return courierSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzeriaJson that)) return false;
        return nBakers == that.nBakers && mCouriers == that.mCouriers && warehouseCapacity == that.warehouseCapacity &&
                Arrays.equals(bakerSpeeds, that.bakerSpeeds) && Arrays.equals(courierSpeed, that.courierSpeed) &&
                timeOfWork == that.timeOfWork;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nBakers, mCouriers, warehouseCapacity, timeOfWork);
        result = 31 * result + Arrays.hashCode(bakerSpeeds);
        result = 31 * result + Arrays.hashCode(courierSpeed);
        return result;
    }
}

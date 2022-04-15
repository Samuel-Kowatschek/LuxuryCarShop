package org.acme.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int CarID;
    private String CarType;
    private String CarModel;
    private String Producer;
    private double Price;
    private boolean isReserved = false;
    private boolean isBought = false;

    public Car() {
    }

    public Car(String carType, String carModel, String producer, double price) {
        setCarType(carType);
        setCarModel(carModel);
        setProducer(producer);
        setPrice(price);
    }

    public Car(String carType, String carModel, String producer, double price, boolean isReserved, boolean isBought) {
        setCarType(carType);
        setCarModel(carModel);
        setProducer(producer);
        setPrice(price);
        setReserved(isReserved);
        setBought(isBought);
    }

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        CarID = carID;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}

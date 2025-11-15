package com.tns.fooddeliverysystem.entities;

public class FoodItem {
    private int id;
    private String name;
    private double price;

    public FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "FoodItem{id=" + id + ", name='" + name + "', price=" + price + "}";
    }

    // Important to use FoodItem as Map key in Cart/Order
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodItem)) return false;
        FoodItem that = (FoodItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

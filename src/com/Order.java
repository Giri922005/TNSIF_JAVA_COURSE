package com.tns.fooddeliverysystem.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items = new LinkedHashMap<>();
    private String status = "Pending";
    private DeliveryPerson deliveryPerson; // nullable
    private String deliveryAddress;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public void setDeliveryPerson(DeliveryPerson deliveryPerson) { this.deliveryPerson = deliveryPerson; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public void addItem(FoodItem item, int quantity) {
        if (item == null || quantity <= 0) return;
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    @Override
    public String toString() {
        String dpText = (deliveryPerson == null) ? "Not Assigned" : deliveryPerson.getName();
        return "Order{orderId=" + orderId +
               ", customer=" + customer.getUsername() +
               ", items=" + items +
               ", status='" + status + "', deliveryPerson=" + dpText + "}";
    }
}

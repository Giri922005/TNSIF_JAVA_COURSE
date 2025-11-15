package com.tns.fooddeliverysystem.Services;

import com.tns.fooddeliverysystem.entities.DeliveryPerson;
import com.tns.fooddeliverysystem.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private List<DeliveryPerson> deliveryPersons = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addDeliveryPerson(DeliveryPerson deliveryPerson) {
        deliveryPersons.add(deliveryPerson);
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

    public void assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        Order order = findOrder(orderId);
        DeliveryPerson dp = findDeliveryPerson(deliveryPersonId);
        if (order != null && dp != null) {
            order.setDeliveryPerson(dp);
        }
    }

    public Order findOrder(int orderId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) return o;
        }
        return null;
    }

    public DeliveryPerson findDeliveryPerson(int id) {
        for (DeliveryPerson d : deliveryPersons) {
            if (d.getDeliveryPersonId() == id) return d;
        }
        return null;
    }
}

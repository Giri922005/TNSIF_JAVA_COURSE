package com.tns.fooddeliverysystem.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<FoodItem, Integer> items = new LinkedHashMap<>();

    public void addItem(FoodItem foodItem, int quantity) {
        if (foodItem == null || quantity <= 0) return;
        items.put(foodItem, items.getOrDefault(foodItem, 0) + quantity);
    }

    public void removeItem(FoodItem foodItem) {
        if (foodItem == null) return;
        items.remove(foodItem);
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{items=" + items + "}";
    }
}

package com.tns.fooddeliverysystem.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<FoodItem> getMenu() { return menu; }

    public void addFoodItem(FoodItem item) {
        // overwrite if id exists
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getId() == item.getId()) {
                menu.set(i, item);
                return;
            }
        }
        menu.add(item);
    }

    public void removeFoodItem(int foodItemId) {
        Iterator<FoodItem> it = menu.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == foodItemId) {
                it.remove();
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "'}";
    }
}

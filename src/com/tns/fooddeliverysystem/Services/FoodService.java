package com.tns.fooddeliverysystem.Services;

import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class FoodService {

    private List<Restaurant> restaurants = new ArrayList<>();

    public List<Restaurant> getRestaurants() { return restaurants; }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> allFoodItems = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            allFoodItems.addAll(restaurant.getMenu());
        }
        return allFoodItems;
    }

    public void addFoodItemToRestaurant(int restaurantId, FoodItem foodItem) {
        Restaurant r = findRestaurant(restaurantId);
        if (r != null) r.addFoodItem(foodItem);
    }

    public void removeFoodItemFromRestaurant(int restaurantId, int foodItemId) {
        Restaurant r = findRestaurant(restaurantId);
        if (r != null) r.removeFoodItem(foodItemId);
    }

    public Restaurant findRestaurant(int id) {
        for (Restaurant r : restaurants) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public FoodItem findFoodItemInRestaurant(int restaurantId, int foodItemId) {
        Restaurant r = findRestaurant(restaurantId);
        if (r == null) return null;
        for (FoodItem fi : r.getMenu()) {
            if (fi.getId() == foodItemId) return fi;
        }
        return null;
    }
}

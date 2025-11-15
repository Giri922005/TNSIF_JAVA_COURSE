package com.tns.fooddeliverysystem.application;

import com.tns.fooddeliverysystem.entities.*;
import com.tns.fooddeliverysystem.Services.*;

import java.util.Map;
import java.util.Scanner;

public class FoodDeliverySystem {

    private final Scanner sc = new Scanner(System.in);
    private final CustomerService customerService = new CustomerService();
    private final FoodService foodService = new FoodService();
    private final OrderService orderService = new OrderService();

    private int nextOrderId = 1;

    public static void main(String[] args) {
        new FoodDeliverySystem().run();
    }

    private void run() {
        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            int opt = readInt();

            if (opt == 1) adminMenu();
            else if (opt == 2) customerMenu();
            else if (opt == 3) break;
        }
    }

    private void adminMenu() {
        System.out.println("\nAdmin Menu:\n");
        while (true) {
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Remove Food Item from Restaurant");
            System.out.println("4. View Restaurants and Menus");
            System.out.println("5. View Orders");
            System.out.println("6. Add Delivery Person");
            System.out.println("7. Assign Delivery Person to Order");
            System.out.println("8. Exit");
            System.out.print("\nChoose an option: ");
            int opt = readInt();

            switch (opt) {
                case 1 -> addRestaurant();
                case 2 -> addFoodItemToRestaurant();
                case 3 -> removeFoodItemFromRestaurant();
                case 4 -> viewRestaurantsAndMenus();
                case 5 -> viewOrders();
                case 6 -> addDeliveryPerson();
                case 7 -> assignDeliveryPersonToOrder();
                case 8 -> {
                    System.out.println("Exiting Admin Module\n");
                    return;
                }
                default -> {}
            }
        }
    }

    private void customerMenu() {
        System.out.println("\nCustomer Menu:\n");
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. View Food Items");
            System.out.println("3. Add Food to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("\nChoose an option: ");
            int opt = readInt();

            switch (opt) {
                case 1 -> addCustomer();
                case 2 -> viewRestaurantsAndMenus();
                case 3 -> addFoodToCart();
                case 4 -> viewCart();
                case 5 -> placeOrder();
                case 6 -> viewOrders();
                case 7 -> {
                    System.out.println("Exiting Customer Module\n");
                    return;
                }
                default -> {}
            }
        }
    }

    // ---- Admin actions

    private void addRestaurant() {
        System.out.print("\nEnter Restaurant ID: ");
        int id = readInt();
        System.out.print("\nEnter Restaurant Name: ");
        String name = readLineNonEmpty();
        foodService.addRestaurant(new Restaurant(id, name));
        System.out.println("\nRestaurant added successfully!\n");
    }

    private void addFoodItemToRestaurant() {
        System.out.print("\nEnter Restaurant ID: ");
        int rid = readInt();
        System.out.print("\nEnter Food Item ID: ");
        int fid = readInt();
        System.out.print("\nEnter Food Item Name: ");
        String fname = readLineNonEmpty();
        System.out.print("\nEnter Food Item Price: ");
        double price = readDouble();

        Restaurant r = foodService.findRestaurant(rid);
        if (r == null) {
            System.out.println("\nRestaurant not found!\n");
            return;
        }
        foodService.addFoodItemToRestaurant(rid, new FoodItem(fid, fname, price));
        System.out.println("Food item added successfully!\n");
    }

    private void removeFoodItemFromRestaurant() {
        System.out.print("\nEnter Restaurant ID: ");
        int rid = readInt();
        System.out.print("\nEnter Food Item ID: ");
        int fid = readInt();

        Restaurant r = foodService.findRestaurant(rid);
        if (r == null) {
            System.out.println("\nRestaurant not found!\n");
            return;
        }
        foodService.removeFoodItemFromRestaurant(rid, fid);
        System.out.println("Food item removed successfully!\n");
    }

    private void viewRestaurantsAndMenus() {
        System.out.println("\nRestaurants and Menus:\n");
        for (Restaurant r : foodService.getRestaurants()) {
            System.out.println("Restaurant ID: " + r.getId() + ", Name: " + r.getName());
            for (FoodItem fi : r.getMenu()) {
                System.out.println("- Food Item ID: " + fi.getId() + ", Name: " + fi.getName() + ", Price: Rs. " + fi.getPrice());
            }
            System.out.println();
        }
    }

    private void addDeliveryPerson() {
        System.out.print("\nEnter Delivery Person ID: ");
        int id = readInt();
        System.out.print("\nEnter Delivery Person Name: ");
        String name = readLineNonEmpty();
        System.out.print("\nEnter Contact No .: ");
        long contact = readLong();
        orderService.addDeliveryPerson(new DeliveryPerson(id, name, contact));
        System.out.println("Delivery person added successfully!\n");
    }

    private void assignDeliveryPersonToOrder() {
        System.out.print("\nEnter Order ID: ");
        int oid = readInt();
        System.out.print("\nEnter Delivery Person ID: ");
        int dpid = readInt();
        orderService.assignDeliveryPersonToOrder(oid, dpid);
        System.out.println("Delivery person assigned to order successfully!\n");
    }

    // ---- Customer actions

    private void addCustomer() {
        System.out.print("\nEnter User ID: ");
        int uid = readInt();
        System.out.print("\nEnter Username: ");
        String uname = readLineNonEmpty();
        System.out.print("\nEnter Contact No .: ");
        long contact = readLong();
        customerService.addCustomer(new Customer(uid, uname, contact));
        System.out.println("\nCustomer created successfully!\n");
    }

    private void addFoodToCart() {
        System.out.print("\nEnter Customer ID: ");
        int uid = readInt();
        Customer c = customerService.getCustomer(uid);
        if (c == null) {
            System.out.println("\nCustomer not found!\n");
            return;
        }
        System.out.print("\nEnter Restaurant ID: ");
        int rid = readInt();
        System.out.print("Enter Food Item ID: ");
        int fid = readInt();
        System.out.print("Enter Quantity: ");
        int qty = readInt();

        FoodItem item = foodService.findFoodItemInRestaurant(rid, fid);
        if (item == null) {
            System.out.println("\nFood item not found in given restaurant!\n");
            return;
        }
        c.getCart().addItem(item, qty);
        System.out.println("Food item added to cart!\n");
    }

    private void viewCart() {
        System.out.print("\nEnter Customer ID: ");
        int uid = readInt();
        Customer c = customerService.getCustomer(uid);
        if (c == null) {
            System.out.println("\nCustomer not found!\n");
            return;
        }

        System.out.println("\nCart:\n");
        double total = 0.0;
        for (Map.Entry<FoodItem, Integer> e : c.getCart().getItems().entrySet()) {
            FoodItem fi = e.getKey();
            int q = e.getValue();
            double cost = fi.getPrice() * q;
            total += cost;
            System.out.println("Food Item: " + fi.getName() + ", Quantity: " + q + ", Cost: Rs. " + cost);
        }
        System.out.println("\nTotal Cost: Rs. " + total + "\n");
    }

    private void placeOrder() {
        System.out.print("\nEnter Customer ID: ");
        int uid = readInt();
        Customer c = customerService.getCustomer(uid);
        if (c == null) {
            System.out.println("\nCustomer not found!\n");
            return;
        }
        Order order = new Order(nextOrderId++, c);
        for (Map.Entry<FoodItem, Integer> e : c.getCart().getItems().entrySet()) {
            order.addItem(e.getKey(), e.getValue());
        }
        orderService.placeOrder(order);
        // Clear cart after placing order (optional; sample implies they view cart before placing)
        // c.getCart().getItems().clear();

        System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId() + "\n");
    }

    private void viewOrders() {
        System.out.println("\nOrders:\n");
        for (Order o : orderService.getOrders()) {
            System.out.println(o);
            System.out.println();
        }
    }

    // ---- Input helpers

    private int readInt() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) { /* retry */ }
        }
    }

    private long readLong() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Long.parseLong(s);
            } catch (Exception e) { /* retry */ }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (Exception e) { /* retry */ }
        }
    }

    private String readLineNonEmpty() {
        while (true) {
            String s = sc.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
        }
    }
}

package com.tns.fooddeliverysystem.Services;

import com.tns.fooddeliverysystem.entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // retrieve Customer by ID
    public Customer getCustomer(int userId) {
        for (Customer c : customerList) {
            if (c.getUserId() == userId) return c;
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customerList;
    }
}

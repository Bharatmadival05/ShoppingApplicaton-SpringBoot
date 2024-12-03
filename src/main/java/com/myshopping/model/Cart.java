package com.myshopping.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private String username;
    private List<CartItem> items;

    public Cart(String username) {
        this.username = username;
        this.items = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.getProductId() == productId);
    }

    public void clearCart() {
        items.clear();
    }

    public int getTotalPrice() {
        return items.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
    }
}

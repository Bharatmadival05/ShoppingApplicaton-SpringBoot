package com.myshopping.service;

import com.myshopping.model.Cart;
import com.myshopping.model.CartItem;

public interface CartDaoService {

	Cart getCartByUsername(String username);
    void addItemToCart(String username, CartItem item);
    void removeItemFromCart(String username, int productId);
    void clearCart(String username);
    void updateItemQuantity(String username, int productId, int quantity);
    public void updateCartItem(String username, CartItem item);
}

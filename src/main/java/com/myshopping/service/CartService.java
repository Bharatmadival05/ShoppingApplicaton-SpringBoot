package com.myshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshopping.model.Cart;
import com.myshopping.model.CartItem;

@Service
public class CartService {

	@Autowired
    private CartDaoService cartDaoService;

    public Cart getCart(String username) {
        return cartDaoService.getCartByUsername(username);
    }

    public void addItemToCart(String username, CartItem item) {
        cartDaoService.addItemToCart(username, item);
    }

    public void removeItemFromCart(String username, int productId) {
        cartDaoService.removeItemFromCart(username, productId);
    }

    public void clearCart(String username) {
        cartDaoService.clearCart(username);
    }
    
    public void updateItemQuantity(String username, int productId, int quantity) {
        cartDaoService.updateItemQuantity(username, productId, quantity);
    }

    public int getTotalPrice(String username) {
        return getCart(username).getTotalPrice();
    }
    
    public void updateCartItem(String username, CartItem item) {
    	cartDaoService.updateCartItem(username, item);
    }
}

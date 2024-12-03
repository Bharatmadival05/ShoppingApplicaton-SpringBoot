package com.myshopping.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.model.Cart;
import com.myshopping.model.CartItem;
import com.myshopping.model.Orders;
import com.myshopping.service.CartService;
import com.myshopping.service.OrdersDaoService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;

    @Autowired
    private OrdersDaoService ordersDaoService;

    @GetMapping("/{username}")
    public Cart getCart(@PathVariable String username) {
        return cartService.getCart(username);
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<Map<String, String>> addItemToCart(@PathVariable String username, @RequestBody CartItem item) {
        cartService.addItemToCart(username, item);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item added to cart");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{username}/remove/{productId}")
    public ResponseEntity<Map<String, String>> removeItemFromCart(@PathVariable String username, @PathVariable int productId) {
        cartService.removeItemFromCart(username, productId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item removed from cart");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{username}/clear")
    public String clearCart(@PathVariable String username) {
        cartService.clearCart(username);
        return "Cart cleared";
    }

    @PostMapping("/{username}/checkout")
    public ResponseEntity<Map<String, String>> checkout(@PathVariable String username) {
        Cart cart = cartService.getCart(username);
        for (CartItem item : cart.getItems()) {
            Orders order = new Orders(0, username, item.getProductId(), item.getQuantity(),
                    item.getPrice() * item.getQuantity(), "Ordered", LocalDateTime.now());
            ordersDaoService.addOrders(order);
        }
        cartService.clearCart(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Order placed successfully");
        return ResponseEntity.ok(response);
    }
    

    @PostMapping("/{username}/checkout/{productId}")
    public String checkoutSingleItem(@PathVariable String username, @PathVariable int productId) {
        Cart cart = cartService.getCart(username);
        for (CartItem item : cart.getItems()) {
            if (item.getProductId() == productId) {
                Orders order = new Orders(0, username, item.getProductId(), item.getQuantity(),
                        item.getPrice() * item.getQuantity(), "Ordered", LocalDateTime.now());
                ordersDaoService.addOrders(order);
                cartService.removeItemFromCart(username, productId);
                return "Order placed for the item successfully";
            }
        }
        return "Item not found in cart";
    }
    
    @PutMapping("/update/{username}/{productId}")
    public ResponseEntity<Map<String, String>> updateItemQuantity(@PathVariable String username, @PathVariable int productId, @RequestParam int quantity) {
        cartService.updateItemQuantity(username, productId, quantity);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item added to cart");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/update/{username}")
    public ResponseEntity<Map<String, String>> updateCartItem(@PathVariable String username, @RequestBody CartItem item) {
        cartService.updateCartItem(username, item);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item updated in cart");
        return ResponseEntity.ok(response);
    }

}

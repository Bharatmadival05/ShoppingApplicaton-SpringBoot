package com.myshopping.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.myshopping.model.Cart;
import com.myshopping.model.CartItem;
import com.myshopping.service.CartDaoService;
import com.myshopping.util.DButil;

@Service
public class CartDaoServiceImpl implements CartDaoService {

private static Connection connection;
    
    public CartDaoServiceImpl() {
        try {
            connection = DButil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart getCartByUsername(String username) {
        Cart cart = new Cart(username);
        String cartQuery = "SELECT id FROM carts WHERE username = ?";
        try (PreparedStatement cartStmt = connection.prepareStatement(cartQuery)) {
            cartStmt.setString(1, username);
            ResultSet cartRs = cartStmt.executeQuery();
            if (cartRs.next()) {
                int cartId = cartRs.getInt("id");
                String itemQuery = "SELECT product_id, quantity, price FROM cart_items WHERE cart_id = ?";
                try (PreparedStatement itemStmt = connection.prepareStatement(itemQuery)) {
                    itemStmt.setInt(1, cartId);
                    ResultSet itemRs = itemStmt.executeQuery();
                    while (itemRs.next()) {
                        CartItem item = new CartItem(itemRs.getInt("product_id"), itemRs.getInt("quantity"), itemRs.getInt("price"));
                        cart.addItem(item);
                    }
                }
            } else {
                // If no cart exists for the user, create a new cart
                createCartForUser(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    
    @Override
    public void addItemToCart(String username, CartItem item) {
        int cartId = getCartIdByUsername(username);
        String query = "INSERT INTO cart_items (cart_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setInt(4, item.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void removeItemFromCart(String username, int productId) {
        int cartId = getCartIdByUsername(username);
        String query = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart(String username) {
        int cartId = getCartIdByUsername(username);
        String query = "DELETE FROM cart_items WHERE cart_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCartIdByUsername(String username) {
        String query = "SELECT id FROM carts WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return createCartForUser(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int createCartForUser(String username) {
        String query = "INSERT INTO carts (username) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    @Override
    public void updateItemQuantity(String username, int productId, int quantity) {
        String query = "UPDATE cart_items SET quantity = ? WHERE cart_id = (SELECT id FROM carts WHERE username = ?) AND product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, username);
            stmt.setInt(3, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateCartItem(String username, CartItem item) {
        int cartId = getCartIdByUsername(username);
        String query = "UPDATE cart_items SET quantity = ?, price = ? WHERE cart_id = ? AND product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getQuantity());
            stmt.setInt(2, item.getPrice());
            stmt.setInt(3, cartId);
            stmt.setInt(4, item.getProductId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

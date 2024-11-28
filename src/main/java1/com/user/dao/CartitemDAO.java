package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.CartItem;

public class CartItemDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String INSERT_CART_ITEM_SQL = "INSERT INTO CartItem (cart_id, product_id, quantity) VALUES (?, ?, ?);";
    private static final String SELECT_CART_ITEMS_BY_CART_ID = "SELECT * FROM CartItem WHERE cart_id = ?;";
    private static final String DELETE_CART_ITEM_SQL = "DELETE FROM CartItem WHERE cart_id = ? AND product_id = ?;";
    private static final String UPDATE_CART_ITEM_SQL = "UPDATE CartItem SET quantity = ? WHERE cart_id = ? AND product_id = ?;";

    public CartItemDAO() {}

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCartItem(CartItem cartItem) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_ITEM_SQL)) {
            preparedStatement.setInt(1, cartItem.getCartId());
            preparedStatement.setInt(2, cartItem.getProductId());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CartItem> selectCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_ITEMS_BY_CART_ID)) {
            preparedStatement.setInt(1, cartId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                cartItems.add(new CartItem(id, cartId, productId, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public boolean deleteCartItem(int cartId, int productId) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_ITEM_SQL)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateCartItem(CartItem cartItem) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_ITEM_SQL)) {
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setInt(2, cartItem.getCartId());
            preparedStatement.setInt(3, cartItem.getProductId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}

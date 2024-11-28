package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Cart;

public class CartDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_CART_SQL = "INSERT INTO Cart (user_id) VALUES (?);";
	private static final String SELECT_CART_BY_USER_ID = "SELECT * FROM Cart WHERE user_id = ?;";
	private static final String DELETE_CART_SQL = "DELETE FROM Cart WHERE user_id = ?;";

	public CartDAO() {
	}

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

	public void insertCart(Cart cart) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_SQL)) {
			preparedStatement.setInt(1, cart.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cart selectCartByUserId(int userId) {
		Cart cart = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_BY_USER_ID)) {
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				int userIdFetched = rs.getInt("user_id");
				cart = new Cart(id, userIdFetched);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	public boolean deleteCart(int userId) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_SQL)) {
			preparedStatement.setInt(1, userId);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}

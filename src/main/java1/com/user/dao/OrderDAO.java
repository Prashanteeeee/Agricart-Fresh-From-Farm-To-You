package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Order;

public class OrderDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_ORDER_SQL = "INSERT INTO `Order` (user_id, total_price, order_date, status) VALUES (?, ?, ?, ?);";
	private static final String SELECT_ORDER_BY_ID = "SELECT * FROM `Order` WHERE id = ?;";
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM `Order`;";
	private static final String DELETE_ORDER_SQL = "DELETE FROM `Order` WHERE id = ?;";
	private static final String UPDATE_ORDER_SQL = "UPDATE `Order` SET total_price = ?, status = ? WHERE id = ?;";

	public OrderDAO() {
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

	public void insertOrder(Order order) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setDouble(2, order.getTotalPrice());
			preparedStatement.setTimestamp(3, order.getOrderDate());
			preparedStatement.setString(4, order.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order selectOrder(int id) {
		Order order = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt("user_id");
				double totalPrice = rs.getDouble("total_price");
				Timestamp orderDate = rs.getTimestamp("order_date");
				String status = rs.getString("status");
				order = new Order(id, userId, totalPrice, orderDate, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public List<Order> selectAllOrders() {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				double totalPrice = rs.getDouble("total_price");
				Timestamp orderDate = rs.getTimestamp("order_date");
				String status = rs.getString("status");
				orders.add(new Order(id, userId, totalPrice, orderDate, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public boolean deleteOrder(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public boolean updateOrder(Order order) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_SQL)) {
			preparedStatement.setDouble(1, order.getTotalPrice());
			preparedStatement.setString(2, order.getStatus());
			preparedStatement.setInt(3, order.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}

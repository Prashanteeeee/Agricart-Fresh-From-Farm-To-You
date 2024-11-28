package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Payment;

public class PaymentDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_PAYMENT_SQL = "INSERT INTO Payment (order_id, amount, payment_date, payment_status) VALUES (?, ?, ?, ?);";
	private static final String SELECT_PAYMENT_BY_ID = "SELECT * FROM Payment WHERE id = ?;";
	private static final String SELECT_ALL_PAYMENTS = "SELECT * FROM Payment;";
	private static final String DELETE_PAYMENT_SQL = "DELETE FROM Payment WHERE id = ?;";

	public PaymentDAO() {
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

	public void insertPayment(Payment payment) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_SQL)) {
			preparedStatement.setInt(1, payment.getOrderId());
			preparedStatement.setDouble(2, payment.getAmount());
			preparedStatement.setTimestamp(3, payment.getPaymentDate());
			preparedStatement.setString(4, payment.getPaymentStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Payment selectPayment(int id) {
		Payment payment = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int orderId = rs.getInt("order_id");
				double amount = rs.getDouble("amount");
				Timestamp paymentDate = rs.getTimestamp("payment_date");
				String paymentStatus = rs.getString("payment_status");
				payment = new Payment(id, orderId, amount, paymentDate, paymentStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payment;
	}

	public List<Payment> selectAllPayments() {
		List<Payment> payments = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				double amount = rs.getDouble("amount");
				Timestamp paymentDate = rs.getTimestamp("payment_date");
				String paymentStatus = rs.getString("payment_status");
				payments.add(new Payment(id, orderId, amount, paymentDate, paymentStatus));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}

	public boolean deletePayment(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}

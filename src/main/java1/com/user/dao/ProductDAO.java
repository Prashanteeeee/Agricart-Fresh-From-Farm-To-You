package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Product;

public class ProductDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// SQL queries
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (name, description, price, stock, category_id) VALUES (?, ?, ?, ?, ?);";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Product WHERE id = ?;";
	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product;";
	private static final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE id = ?;";
	private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET name = ?, description = ?, price = ?, stock = ?, category_id = ? WHERE id = ?;";

	// Constructor
	public ProductDAO() {
	}

	// Establish connection to the database
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

	// Insert a new product
	public void insertProduct(Product product) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getStock());
			preparedStatement.setInt(5, product.getCategoryId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select a product by ID
	public Product selectProduct(int id) {
		Product product = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				int categoryId = rs.getInt("category_id");
				product = new Product(id, name, description, price, stock, categoryId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// Select all products
	public List<Product> selectAllProducts() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				int categoryId = rs.getInt("category_id");
				products.add(new Product(id, name, description, price, stock, categoryId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	// Delete a product by ID
	public boolean deleteProduct(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	// Update an existing product
	public boolean updateProduct(Product product) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getStock());
			preparedStatement.setInt(5, product.getCategoryId());
			preparedStatement.setInt(6, product.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}

package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Category;

public class CategoryDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_CATEGORY_SQL = "INSERT INTO Category (name) VALUES (?);";
	private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE id = ?;";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Category;";
	private static final String DELETE_CATEGORY_SQL = "DELETE FROM Category WHERE id = ?;";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE Category SET name = ? WHERE id = ?;";

	public CategoryDAO() {
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

	public void insertCategory(Category category) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
			preparedStatement.setString(1, category.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Category selectCategory(int id) {
		Category category = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				category = new Category(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	public List<Category> selectAllCategories() {
		List<Category> categories = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				categories.add(new Category(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public boolean deleteCategory(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public boolean updateCategory(Category category) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {
			preparedStatement.setString(1, category.getName());
			preparedStatement.setInt(2, category.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}

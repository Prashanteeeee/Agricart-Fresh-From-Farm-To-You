package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.User;

public class UserDAO {
	
	 private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
	    private String jdbcUsername = "root";
	    private String jdbcPassword = "password";

	    private static final String INSERT_USER_SQL = "INSERT INTO User (name, email, address, phone, password) VALUES (?, ?, ?, ?, ?);";
	    private static final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE id = ?;";
	    private static final String SELECT_ALL_USERS = "SELECT * FROM User;";
	    private static final String DELETE_USER_SQL = "DELETE FROM User WHERE id = ?;";
	    private static final String UPDATE_USER_SQL = "UPDATE User SET name = ?, email = ?, address = ?, phone = ?, password = ? WHERE id = ?;";
		public UserDAO() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
		public Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }

	    // Insert user into the database
	    public void insertUser(User user) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
	            preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getEmail());
	            preparedStatement.setString(3, user.getAddress());
	            preparedStatement.setString(4, user.getPhoneNo());
	            preparedStatement.setString(5, user.getPassword());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Select user by ID
	    public User selectUser(int id) {
	        User user = null;
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();
	            if (rs.next()) {
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String address = rs.getString("address");
	                String phone = rs.getString("phone");
	                String password = rs.getString("password");
	                user = new User(id, name, email, address, phone, password);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	    }

	    // Select all users from the database
	    public List<User> selectAllUsers() {
	        List<User> users = new ArrayList<>();
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String address = rs.getString("address");
	                String phone = rs.getString("phone");
	                String password = rs.getString("password");
	                users.add(new User(id, name, email, address, phone, password));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }

	    // Delete user by ID
	    public boolean deleteUser(int id) {
	        boolean rowDeleted = false;
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
	            preparedStatement.setInt(1, id);
	            rowDeleted = preparedStatement.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rowDeleted;
	    }

	    // Update user details
	    public boolean updateUser(User user) {
	        boolean rowUpdated = false;
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
	            preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getEmail());
	            preparedStatement.setString(3, user.getAddress());
	            preparedStatement.setString(4, user.getPhoneNo());
	            preparedStatement.setString(5, user.getPassword());
	            preparedStatement.setFloat(6, user.getUserId());
	            rowUpdated = preparedStatement.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rowUpdated;
	    }

}

package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Farmer;

public class FarmerDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/agricart";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    // SQL queries
    private static final String INSERT_FARMER_SQL = "INSERT INTO Farmer (name, email, address, phone, farmName, cropType) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_FARMER_BY_ID = "SELECT * FROM Farmer WHERE id = ?;";
    private static final String SELECT_ALL_FARMERS = "SELECT * FROM Farmer;";
    private static final String DELETE_FARMER_SQL = "DELETE FROM Farmer WHERE id = ?;";
    private static final String UPDATE_FARMER_SQL = "UPDATE Farmer SET name = ?, email = ?, address = ?, phone = ?, farmName = ?, cropType = ? WHERE id = ?;";

    // Constructor
    public FarmerDAO() {
        super();
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

    // Insert a new farmer
    public void insertFarmer(Farmer farmer) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FARMER_SQL)) {
            preparedStatement.setString(1, farmer.getName());
            preparedStatement.setString(2, farmer.getEmail());
            preparedStatement.setString(3, farmer.getAddress());
            preparedStatement.setString(4, farmer.getPhoneNo());
            preparedStatement.setString(5, farmer.getFarmName());
            preparedStatement.setString(6, farmer.getCropType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select a farmer by ID
    public Farmer selectFarmer(int id) {
        Farmer farmer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FARMER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String farmName = rs.getString("farmName");
                String cropType = rs.getString("cropType");
                farmer = new Farmer(id, name, email, address, phone, farmName, cropType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmer;
    }

    // Select all farmers
    public List<Farmer> selectAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FARMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String farmName = rs.getString("farmName");
                String cropType = rs.getString("cropType");
                farmers.add(new Farmer(id, name, email, address, phone, farmName, cropType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmers;
    }

    // Delete a farmer by ID
    public boolean deleteFarmer(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FARMER_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update an existing farmer
    public boolean updateFarmer(Farmer farmer) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FARMER_SQL)) {
            preparedStatement.setString(1, farmer.getName());
            preparedStatement.setString(2, farmer.getEmail());
            preparedStatement.setString(3, farmer.getAddress());
            preparedStatement.setString(4, farmer.getPhoneNo());
            preparedStatement.setString(5, farmer.getFarmName());
            preparedStatement.setString(6, farmer.getCropType());
            preparedStatement.setInt(7, farmer.getFarmerId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}

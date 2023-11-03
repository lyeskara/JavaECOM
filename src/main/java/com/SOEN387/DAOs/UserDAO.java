package com.SOEN387.DAOs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SOEN387.configs.DatabaseConnection;
import com.SOEN387.models.User;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public User findByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM users WHERE name = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getBoolean("isAdmin")
                        );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public int getUserIDByUsername(String username) {
        String query = "SELECT id FROM users WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return a negative value (e.g., -1) to indicate that the user was not found.
    }

    // Implement other methods for user-related operations, e.g., createUser, updateUser, deleteUser

    public void close() {
        DatabaseConnection.closeConnection();
    }
}
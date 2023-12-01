package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void registerNewUser( User user) {
        if (getUserByName(user.getUsername()) != null) {   // checking if user is already exist
            System.out.println("User with username " + user.getUsername() + " already exists. Registration failed.");
            return;
        }
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("INSERT INTO users (username, password, role) VALUES (?, ?, ?);")) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?;");

            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByName(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

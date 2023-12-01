package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    public void addAuthor( Author author) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("INSERT INTO authors (id,name) VALUES (?, ?);")) {

            ps.setInt(1, author.getId());
            ps.setString(2, author.getName());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeAuthor(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement("UPDATE books SET authorId = NULL WHERE authorId = ?");
            pst.setInt(1,id);
            pst.executeUpdate();

            PreparedStatement ps = connection.prepareStatement("DELETE FROM authors WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAuthor(Author author) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE authors SET name = ? WHERE id = ?;");

            ps.setString(1, author.getName());
            ps.setInt(2, author.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Author> getAllAuthors() {
        List<Author> authorsList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
            while (rs.next()) {
                Author author = new Author(rs.getInt("id"),
                        rs.getString("name"));
                authorsList.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorsList;
    }
    public Author getAuthorById(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM authors WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Author(rs.getInt("id"),
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

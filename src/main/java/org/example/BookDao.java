package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// CRUD methods
public class BookDao {

    public void createBook( Book book) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("INSERT INTO books (title, author, price, quantity,authorId) VALUES (?, ?, ?, ?,?);")) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAuthorId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeBook(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE books SET price = ?, quantity = ? WHERE id = ?;");

            ps.setDouble(1, book.getPrice());
            ps.setInt(2, book.getQuantity());
            ps.setInt(3, book.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> getAllBook() {
        List<Book> booksList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("authorId"));

                booksList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }
    public Book getBookById(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("authorId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

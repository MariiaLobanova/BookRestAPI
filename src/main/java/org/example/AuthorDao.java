package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    public void addAuthor( Author author) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     ("INSERT INTO authors (id,name,country) VALUES (?, ?,?);")) {

            ps.setInt(1, author.getId());
            ps.setString(2, author.getName());
            ps.setString(3, author.getCountry());

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
            PreparedStatement ps = connection.prepareStatement("UPDATE authors SET name = ?,country =? WHERE id = ?;");

            ps.setString(1, author.getName());
            ps.setString(2, author.getCountry());
            ps.setInt(3, author.getId());

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
                        rs.getString("name"),
                        rs.getString("country"));
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
                        rs.getString("name"),
                        rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<AuthorBook> getAuthorsCountry(){
        List<AuthorBook> countryList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT authors.country, authors.name, books.title " +
                    "FROM authors RIGHT JOIN books ON books.authorId = authors.id");
            while (rs.next()) {
                Author author = new Author(rs.getString("country"),
                        rs.getString("name"));
                Book book = new Book(rs.getString("title"));
                AuthorBook authorBook = new AuthorBook(author, book);
                countryList.add(authorBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }
}

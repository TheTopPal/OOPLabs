package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model_library {
    private List<Book> library;

    public Model_library() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydbtest", "root", "Admin");
        library = readLibraryFromDatabase(connection);
        connection.close();
    }

    public void addBook(Book book) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydbtest", "root", "Admin");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getPublisher());
        statement.setInt(4, book.getYear());
        statement.setInt(5, book.getPages());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public List<Book> getLibrary() {
        return library;
    }

    private List<Book> readLibraryFromDatabase(Connection connection) throws SQLException {
        List<Book> library = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            int year = resultSet.getInt("year");
            int pages = resultSet.getInt("pages");
            Book book = new Book(title, author, publisher, year, pages);
            library.add(book);
        }
        resultSet.close();
        statement.close();
        return library;
    }
}

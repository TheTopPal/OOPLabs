package com.example;

import com.example.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Model_library {
    private List<Book> library;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASS = "Admin";

    public Model_library() throws IOException, SQLException {

        library = readLibraryFromDB();
    }

    public void addBook(Book book) throws IOException, SQLException {
        library.add(book);
        insertBookToDB(book);
    }

    public void updateBook(Book book) throws IOException, SQLException {
        updateBookInDB(book);
    }

    public void deleteBook(Book book) throws IOException, SQLException {
        library.remove(book);
        deleteBookFromDB(book);
    }

    public List<Book> getLibrary(){
        return library;
    }

    private List<Book> readLibraryFromDB() throws SQLException {
        List<Book> library = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        Statement stmt = conn.createStatement();

        String sql = "SELECT title, author, publisher, year, pages FROM books";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            int year = rs.getInt("year");
            int pages = rs.getInt("pages");
            Book book = new Book(title, author, publisher, year, pages);
            library.add(book);
        }

        rs.close();
        stmt.close();
        conn.close();

        return library;
    }

    private void insertBookToDB(Book book) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql = "INSERT INTO books (title, author, publisher, year, pages) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getPublisher());
        statement.setInt(4, book.getYear());
        statement.setInt(5, book.getPages());

        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    private void updateBookInDB(Book book) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql = "UPDATE books SET title=?, author=?, publisher=?, year=?, pages=? WHERE title=? AND author=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getPublisher());
        statement.setInt(4, book.getYear());
        statement.setInt(5, book.getPages());
        statement.setString(6, book.getTitle());
        statement.setString(7, book.getAuthor());

        statement.executeUpdate();

        statement.close();
        conn.close();
    }

    private void deleteBookFromDB(Book book) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String sql = "DELETE FROM books WHERE title=? AND author=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());

        statement.executeUpdate();

        statement.close();
        conn.close();
    }
}
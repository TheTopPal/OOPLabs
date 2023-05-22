package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "ControllerServlet", value = "/books")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Book> library = new ArrayList<>();
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public void init(){
        // Connect to the database on servlet initialization
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydbtest";
            String user = "root";
            String password = "Admin";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("year");
                int pages = rs.getInt("pages");
                Book book = new Book(title, author, publisher, year, pages);
                library.add(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Convert library list to JSON and send as the response
        String json = new Gson().toJson(library);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read JSON data from request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        // Convert JSON to Book object
        Book newBook = new Gson().fromJson(jsonData, Book.class);

        // Add the new book to the library
        // Write library back to database
        try {
            stmt = conn.prepareStatement("INSERT INTO books (title, author, publisher, year, pages) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, newBook.getTitle());
            stmt.setString(2, newBook.getAuthor());
            stmt.setString(3, newBook.getPublisher());
            stmt.setInt(4, newBook.getYear());
            stmt.setInt(5, newBook.getPages());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        library.add(newBook);

        // Convert updated library list to JSON and send as the response
        String json = new Gson().toJson(library);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
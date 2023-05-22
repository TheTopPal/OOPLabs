package com.example;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int pages;

    public Book(String title, String author, String publisher, int year, int pages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
    }

    // getters setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}

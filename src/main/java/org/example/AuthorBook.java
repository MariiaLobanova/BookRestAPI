package org.example;

public class AuthorBook {
    private String country;
    private String name;
    private String title;


    public AuthorBook(Author author, Book book) {  // for query with join
        this.country = author.getCountry();
        this.name = author.getName();
        this.title = book.getTitle();

    }
    public AuthorBook(){}

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}

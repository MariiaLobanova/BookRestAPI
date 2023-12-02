package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        String BASE_URI = "http://localhost:8040/";
        ResourceConfig resourceConfig = new ResourceConfig(BookResourse.class, AuthorResourse.class, UserResourse.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
        System.out.println("Server started at: " + BASE_URI);


        //Book book = new Book(2,"D-DAy", "Beevor", 12.99,5);
       // BookDao bd = new BookDao();
       // bd.createBook(book);

    }
}

package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BookResourse {
    BookDao bookDao = new BookDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBook() {
        return bookDao.getAllBook();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) {

        return bookDao.getBookById(id);
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook (Book book) {
        bookDao.createBook(book);
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id") int id, Book book) {
        book.setId(id);
        bookDao.updateBook(book);
    }
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") int id) {
        bookDao.removeBook(id);
    }

}

package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getBookById(@PathParam("id") int id) {
        Book book = bookDao.getBookById(id);

        if (book != null) {
            return Response.ok().entity(book).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book with ID " + id + " not found")
                    .build();
        }
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
    public Response updateBook(@PathParam("id") int id, Book book) {
        Book existingBook = bookDao.getBookById(id);
        if (existingBook != null) {
            bookDao.updateBook(book);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book with ID " + id + "not found")
                    .build();
        }

    }
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book existingBook = bookDao.getBookById(id);

        if (existingBook != null) {
            bookDao.removeBook(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book with ID " + id + "not found")
                    .build();
        }
    }

}

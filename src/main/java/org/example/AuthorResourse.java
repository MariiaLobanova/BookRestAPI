package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
public class AuthorResourse {

    AuthorDao authorDao = new AuthorDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAuthor() {
        return authorDao.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(@PathParam("id") int id) {
        Author author = authorDao.getAuthorById(id);

        if (author != null) {
            return Response.ok().entity(author).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Author with ID " + id + " not found")
                    .build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAuthor (Author author) {
        authorDao.addAuthor(author);
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        Author existAuthor = authorDao.getAuthorById(id);

        if (existAuthor != null) {
            author.setId(id);
            authorDao.updateAuthor(author);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Author with ID " + id + " not found")
                    .build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author existingAuthor = authorDao.getAuthorById(id);

        if (existingAuthor != null) {
            authorDao.removeAuthor(id);
            return Response.noContent().build();
        } else{
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Author with ID " + id + "not found")
                    .build();
        }
    }
    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AuthorBook> getAutorsCountry() {
        return authorDao.getAuthorsCountry();
    }

}

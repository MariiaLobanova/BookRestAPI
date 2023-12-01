package org.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/users")
public class UserResourse {
    UserDao userDao = new UserDao();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user){
        userDao.registerNewUser(user);
        return Response.ok().build();
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user){
        User existUser = userDao.getUserByName(user.getUsername());

        if(existUser!=null&& existUser.getPassword().equals(user.getPassword())){
            return Response.ok().entity("Logged in").build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).entity("Login failed").build();
        }
    }
}

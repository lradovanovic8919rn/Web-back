package rs.raf.demo.resources;

import rs.raf.demo.entities.User;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> allUser() {
        return this.userService.allUser();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return this.userService.addUser(user);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User specificUser(@PathParam("email") String email) {
        return this.userService.specificUser(email);
    }

    @PUT
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user, @PathParam("email") String email) {
        return this.userService.updateUser(user, email);
    }

    @PUT
    @Path("/status/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeStatus(@PathParam("email") String email) {
        this.userService.changeStatus(email);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getHashedPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

}

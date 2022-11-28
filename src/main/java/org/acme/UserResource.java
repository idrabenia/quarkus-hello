package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    UserJdbcRepository userRepository;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String create(User user) {
        userRepository.create(user.getName());

        return "User created successfully";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        var result = userRepository.findAll();

        return Response.ok().entity(result).build();
    }

}
